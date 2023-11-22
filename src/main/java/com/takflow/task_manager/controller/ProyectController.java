package com.takflow.task_manager.controller;

import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.service.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/project")
public class ProyectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @PostMapping("/add-user")
    public ResponseEntity<HttpStatus> addMemberToProyect(@RequestBody long proyectId, long userId){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDtoResponse> createProject(@Valid @RequestBody ProjectDtoRequest projectDtoRequest){

        return new ResponseEntity<>(
                projectServiceImpl.createProject(projectDtoRequest)
                ,HttpStatus.CREATED);
    }

}
