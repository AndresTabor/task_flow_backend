package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.ProjectMapper;
import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.IsActive;
import com.takflow.task_manager.model.enums.MemberRol;
import com.takflow.task_manager.repository.ProjectRepository;
import com.takflow.task_manager.repository.ProjectSummaryProjection;
import com.takflow.task_manager.service.interfaces.ProjectService;
import com.takflow.task_manager.service.interfaces.UserProjectService;
import com.takflow.task_manager.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {
    //TODO Implements ExceptionHandler
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectService userProjectService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public ProjectDtoResponse createProject(ProjectDtoRequest project) {
        Project projectToCreate = ProjectMapper.INSTANCE.dtoToProject(project);
        Project newProject = projectRepository.save(projectToCreate);
        //Add Owner
        User owner = getMember(project.getOwnerId());
        userProjectService.addMemberToProject(newProject,owner,MemberRol.OWNER);
        return ProjectMapper.INSTANCE.projectToDto(newProject);
    }

    //TODO: Implement Security Admin
    @Override
    public ProjectDtoResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        return  ProjectMapper.INSTANCE.projectToDto(project);
    }


    //TODO: Implement Security Admin
    @Override
    public List<ProjectDtoResponse> getAllProjects(Long userId) {
        return null;
    }

    //TODO: Implement Security Admin
    @Override
    public void deleteProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setIsActive(IsActive.DISABLED);
    }
    @Transactional
    @Override
    public void deleteProjectsAsOwner(Long projectId,Long userId) throws AccessDeniedException {
        Project project = projectRepository.findById(projectId).orElseThrow(() ->
                new NoSuchElementException("Project not found")
        );

        Optional<UserProject> member = getUserProject(userId, project);
        if(!isOwner(member)){
            throw new AccessDeniedException("You do not have the permissions to perform this action");
        }
        project.setIsActive(IsActive.DISABLED);
    }



    @Override
    public List<ProjectSummaryProjection> getParticipatingProjects(Long id) {
        return projectRepository.findParticipatingProjects(id);
    }

    @Transactional
    @Override
    public ProjectDtoResponse addMember(Long projectId, Long memberId, Long ownerId) throws AccessDeniedException {
        Project project = projectRepository.findById(projectId).orElseThrow();

        Optional<UserProject> owner = getUserProject(ownerId, project);
        if(!isOwner(owner)){
            throw new AccessDeniedException("You do not have the permissions to perform this action");
        }

        Optional<UserProject> memberIsPresent = getUserProject(memberId, project);
        if (memberIsPresent.isPresent()){
            throw new AccessDeniedException("The member has already been added");
        }

        User memberToAdd = getMember(memberId);
        userProjectService.addMemberToProject(project,memberToAdd,MemberRol.MEMBER);
        return ProjectMapper.INSTANCE.projectToDto(project);
    }
    @Transactional
    @Override
    public void addTaskToProject(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId).orElseThrow(() ->
                new NoSuchElementException("El proyecto no se encuentra")
        );

        project.getTasks().add(task);
    }

    private User getMember(Long memberId){
        UserDtoResponse member = userService.getUserById(memberId);
        return UserMapper.INSTANCE.dtoToUser(member);
    }

    private boolean isOwner(Optional<UserProject> member) {
        return member.filter(userProject -> userProject.getMemberRol() == MemberRol.OWNER).isPresent();
    }

    private Optional<UserProject> getUserProject(Long userId, Project project) {
        return project.getMembers()
                .stream()
                .filter(userProject -> userProject.getUser().getId().equals(userId))
                .findFirst();

    }
}
