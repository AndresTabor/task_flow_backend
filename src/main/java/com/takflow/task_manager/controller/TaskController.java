package com.takflow.task_manager.controller;

import com.takflow.task_manager.dto.request.AssignMemberDto;
import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/new")
    public ResponseEntity<TaskDtoResponse> createTaks(@RequestBody TaskDtoRequest newTask){
        return new ResponseEntity<>(taskService.createTask(newTask),
                HttpStatus.CREATED);
    }

    @PostMapping("assing")
    public ResponseEntity<TaskDtoResponse> assignMember(@RequestBody AssignMemberDto assignMemberDto){
        return new ResponseEntity<>(taskService.assignMember(
                assignMemberDto.getProjectId(),assignMemberDto.getTaskId(),assignMemberDto.getMemberId()),
                HttpStatus.CREATED);
    }
}
