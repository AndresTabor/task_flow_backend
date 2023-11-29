package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.TaskDtoResponse;

public interface TaskService {

    TaskDtoResponse createTask(TaskDtoRequest newTask);
}
