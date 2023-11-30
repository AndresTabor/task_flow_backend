package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.ProjectMapper;
import com.takflow.task_manager.config.mapper.TaskMapper;
import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.dto.response.UserProjectDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.TaskState;
import com.takflow.task_manager.repository.TaskRepository;
import com.takflow.task_manager.service.interfaces.ProjectService;
import com.takflow.task_manager.service.interfaces.TaskService;
import com.takflow.task_manager.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public TaskDtoResponse createTask(TaskDtoRequest newTask) {
        Task task = TaskMapper.INSTANCE.dtoToTask(newTask);
        projectService.addTaskToProject(newTask.getProjectId(),task);
        return TaskMapper.INSTANCE.taskToDto(taskRepository.save(task));
    }

    public TaskDtoResponse assignMember(Long projectId,Long taskId, Long userId){
        ProjectDtoResponse project = projectService.getProjectById(projectId);

        Optional<UserProjectDtoResponse> memberToAssign = project.getMembers()
                .stream()
                .filter(userProject -> userProject.getUser().getId().equals(userId))
                .findFirst();


        if (memberToAssign.isEmpty()){
            throw new NoSuchElementException("El usuario no se encuentra asignado al proyecto");
        }

        Task task = taskRepository.findById(taskId).orElseThrow();
        User userToAssign = UserMapper.INSTANCE.dtoToUser(userService.getUserById(userId));
        task.setAssignedMember(userToAssign);
        task.setState(TaskState.ASSIGNED);
        return TaskMapper.INSTANCE.taskToDto(taskRepository.save(task));

    }
}
