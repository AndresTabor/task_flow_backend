package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.ProjectDtoRequest;

import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.repository.ProjectSummaryProjection;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ProjectService {
    ProjectDtoResponse createProject(ProjectDtoRequest project);

    ProjectDtoResponse getProjectById(Long id);

    List<ProjectDtoResponse> getAllProjects();

    void deleteProjectById(Long projectId);

    void deleteProjectsAsOwner(Long projectId,Long userId) throws AccessDeniedException;

    List<ProjectSummaryProjection> getParticipatingProjects(Long id);

    ProjectDtoResponse addMember(Long projectId, Long memberId, Long ownerId) throws AccessDeniedException;

    void addTaskToProject(Long projectId, Task task);
}
