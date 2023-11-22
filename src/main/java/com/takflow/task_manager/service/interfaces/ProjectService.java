package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.model.Project;

import java.util.List;

public interface ProjectService {
    ProjectDtoResponse createProject(ProjectDtoRequest project);

    Project getProjectById(Long id);

    List<ProjectDtoResponse> getAllProjects(Long userId);

    List<ProjectDtoResponse> getProjectsAsOwner(Long ownerId);

    void deleteProjectById(Long projectId);

    void deleteProjectsAsOwner();
}
