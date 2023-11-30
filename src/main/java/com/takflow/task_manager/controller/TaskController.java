package com.takflow.task_manager.controller;

import com.takflow.task_manager.dto.request.AssignMemberDto;
import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.service.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;


    @PostMapping("/new")
    public ResponseEntity<TaskDtoResponse> createTaks(@Valid @RequestBody TaskDtoRequest newTask){
        return new ResponseEntity<>(taskService.createTask(newTask),
                HttpStatus.CREATED);
    }

    @PostMapping("assign")
    public ResponseEntity<TaskDtoResponse> assignMember(@Valid @RequestBody AssignMemberDto assignMemberDto){
        return new ResponseEntity<>(taskService.assignMember(
                assignMemberDto.getProjectId(),assignMemberDto.getTaskId(),assignMemberDto.getMemberId()),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDtoResponse> getTaskById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTaskById(id),HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<TaskDtoResponse>> getAllTask(){
        return new ResponseEntity<>(taskService.getAllTask(),HttpStatus.OK);
    }

    @GetMapping("/my-tasks/{id}")
    public ResponseEntity<List<TaskDtoResponse>> getTasksAssigned(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTasksAssigned(id),HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id){

        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
