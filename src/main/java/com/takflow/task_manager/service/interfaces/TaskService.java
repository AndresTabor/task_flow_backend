package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.TaskDtoResponse;

import java.util.List;

public interface TaskService {

    TaskDtoResponse createTask(TaskDtoRequest newTask);

    TaskDtoResponse assignMember(Long projectId,Long taskId, Long userId);

    TaskDtoResponse getTaskById(Long taskId);

    List<TaskDtoResponse> getTasksAssigned(Long userId);

    void deleteTask(Long taskId);
}
