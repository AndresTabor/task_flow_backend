package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.ProjectMapper;
import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.enums.MemberRol;
import com.takflow.task_manager.repository.ProjectRepository;
import com.takflow.task_manager.service.interfaces.ProjectService;
import com.takflow.task_manager.service.interfaces.UserProjectService;
import com.takflow.task_manager.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ProjectDtoResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        return  ProjectMapper.INSTANCE.projectToDto(project);
    }



    @Override
    public List<ProjectDtoResponse> getAllProjects(Long userId) {
        return null;
    }

    @Override
    public List<ProjectDtoResponse> getProjectsAsOwner(Long ownerId) {
        return null;
    }

    @Override
    public void deleteProjectById(Long projectId) {

    }

    @Override
    public void deleteProjectsAsOwner() {

    }

    private User getOwner(Long ownerId){
        UserDtoResponse getOwner = userService.getUserById(ownerId);
        return UserMapper.INSTANCE.dtoToUser(getOwner);
    }
}
