package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.ProjectMapper;
import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.Project;
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

import java.util.List;
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
        User owner = getOwner(project.getOwnerId());
        userProjectService.addMemberToProject(newProject,owner,MemberRol.OWNER);
        return ProjectMapper.INSTANCE.projectToDto(newProject);
    }

    //TODO: Implement Security Admin
    @Override
    public ProjectDtoResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        return  ProjectMapper.INSTANCE.projectToDto(project);
    }



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
    public void deleteProjectsAsOwner(Long projectId,Long userId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        UserProject member = project.getMembers()
                .stream()
                .filter(userProject -> userProject.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow();

        MemberRol role = member.getMemberRol();

        if (role != MemberRol.OWNER){
            throw new RuntimeException("No tienes los permisos para eliminar el proyecto");
        }
        project.setIsActive(IsActive.DISABLED);
    }

    @Override
    public List<ProjectSummaryProjection> getParticipatingProjects(Long id) {
        return projectRepository.findParticipatingProjects(id);
    }

    private User getOwner(Long ownerId){
        UserDtoResponse getOwner = userService.getUserById(ownerId);
        return UserMapper.INSTANCE.dtoToUser(getOwner);
    }
}
