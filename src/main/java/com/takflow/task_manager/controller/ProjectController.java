package com.takflow.task_manager.controller;

import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.repository.ProjectSummaryProjection;
import com.takflow.task_manager.service.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @PostMapping("/add-user")
    public ResponseEntity<HttpStatus> addMemberToProyect(@RequestBody long proyectId, long userId){
        //TODO: Implement add member
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDtoResponse> createProject(@Valid @RequestBody ProjectDtoRequest projectDtoRequest){

        return new ResponseEntity<>(
                projectServiceImpl.createProject(projectDtoRequest)
                ,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> getProjectById(@PathVariable Long id){
        return new ResponseEntity<>(
                projectServiceImpl.getProjectById(id),
                HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<ProjectSummaryProjection>> getParticipatingProjects(@PathVariable Long id){
        return new ResponseEntity<>(
                projectServiceImpl.getParticipatingProjects(id),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProjectsAsOwner(@PathVariable Long id){
        projectServiceImpl.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{ownerId}/{projectId}")
    public ResponseEntity<HttpStatus> deleteProjectsAsOwner(@PathVariable Long ownerId, @PathVariable Long projectId){
        projectServiceImpl.deleteProjectsAsOwner(projectId,ownerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
