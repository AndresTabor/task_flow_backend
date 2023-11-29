package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.TaskMapper;
import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.repository.TaskRepository;
import com.takflow.task_manager.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public TaskDtoResponse createTask(TaskDtoRequest newTask) {
        Task task = TaskMapper.INSTANCE.dtoToTask(newTask);
        return TaskMapper.INSTANCE.taskToDto(taskRepository.save(task));
    }
}
