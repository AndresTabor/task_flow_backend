package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.ProjectMapper;
import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.exception.EntityNotFoundException;
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
import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectService userProjectService;

    @Autowired
    private UserService userService;

    public static final String PROJECT_NOT_FOUND = "Project not found with ID: ";

    public static final String ACCESDENIED = "You do not have the permissions to perform this action";

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
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND + id));
        return  ProjectMapper.INSTANCE.projectToDto(project);
    }


    //TODO: Implement Security Admin
    @Override
    public List<ProjectDtoResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectMapper.INSTANCE::projectToDto)
                .toList();
    }

    //TODO: Implement Security Admin
    @Override
    public void deleteProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND + projectId));
        project.setIsActive(IsActive.DISABLED);
    }
    @Transactional
    @Override
    public void deleteProjectsAsOwner(Long projectId,Long userId) throws AccessDeniedException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND + projectId));

        MemberRol userRole = userProjectService.getRoleInProject(userId,projectId);
        if (userRole != MemberRol.OWNER){
            throw new AccessDeniedException(ACCESDENIED);
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
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND + projectId));

        MemberRol userRole = userProjectService.getRoleInProject(ownerId,projectId);
        if (userRole != MemberRol.OWNER){
            throw new AccessDeniedException(ACCESDENIED);
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
    public ProjectDtoResponse removeMember(Long projectId, Long memberId, Long ownerId) throws AccessDeniedException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND + projectId));

        MemberRol userRole = userProjectService.getRoleInProject(ownerId,projectId);
        if (userRole != MemberRol.OWNER){
            throw new AccessDeniedException(ACCESDENIED);
        }

        userProjectService.removeMemberToProject(memberId,projectId);
        return ProjectMapper.INSTANCE.projectToDto(project);
    }

    @Transactional
    @Override
    public void addTaskToProject(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(PROJECT_NOT_FOUND + projectId));

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
