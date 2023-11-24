package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.ProjectDtoRequest;

import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.repository.ProjectSummaryProjection;

import java.util.List;

public interface ProjectService {
    ProjectDtoResponse createProject(ProjectDtoRequest project);

    ProjectDtoResponse getProjectById(Long id);

    List<ProjectDtoResponse> getAllProjects(Long userId);

    void deleteProjectById(Long projectId);

    void deleteProjectsAsOwner(Long projectId,Long userId);

    List<ProjectSummaryProjection> getParticipatingProjects(Long id);
}
