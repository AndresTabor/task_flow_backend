package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.TaskMapper;
import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.dto.response.UserProjectDtoResponse;
import com.takflow.task_manager.exception.EntityNotFoundException;
import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.enums.TaskState;
import com.takflow.task_manager.repository.TaskRepository;
import com.takflow.task_manager.service.interfaces.ProjectService;
import com.takflow.task_manager.service.interfaces.TaskService;
import com.takflow.task_manager.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    public static final String TASK_NOT_FOUND = "Task not found with ID: ";

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
            throw new EntityNotFoundException("The member must be assigned to the project");
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->  new EntityNotFoundException(TASK_NOT_FOUND + taskId));

        User userToAssign = UserMapper.INSTANCE.dtoToUser(userService.getUserById(userId));
        task.setAssignedMember(userToAssign);
        task.setState(TaskState.ASSIGNED);
        return TaskMapper.INSTANCE.taskToDto(taskRepository.save(task));

    }

    @Override
    public TaskDtoResponse getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(TASK_NOT_FOUND + taskId));
        return TaskMapper.INSTANCE.taskToDto(task);
    }

    @Override
    public List<TaskDtoResponse> getTasksAssigned(Long userId) {
        List<Task> tasks = taskRepository.getTasksAssigned(userId);

        return tasks.stream()
                .map(TaskMapper.INSTANCE::taskToDto)
                .toList();
    }
    //TODO: Implement security Admin
    @Override
    public List<TaskDtoResponse> getAllTask() {
         List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper.INSTANCE::taskToDto)
                .toList();
    }


    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->  new EntityNotFoundException(TASK_NOT_FOUND + taskId));
        taskRepository.delete(task);
    }
}
