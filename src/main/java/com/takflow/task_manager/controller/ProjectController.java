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

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

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

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDtoResponse>> getAllProject(){
        return new ResponseEntity<>(
                projectServiceImpl.getAllProjects(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProjectsAsOwner(@PathVariable Long id){
        projectServiceImpl.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{ownerId}/{projectId}")
    public ResponseEntity<HttpStatus> deleteProjectsAsOwner(@PathVariable Long ownerId, @PathVariable Long projectId) throws AccessDeniedException {
        projectServiceImpl.deleteProjectsAsOwner(projectId,ownerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<ProjectSummaryProjection>> getParticipatingProjects(@PathVariable Long id){
        return new ResponseEntity<>(
                projectServiceImpl.getParticipatingProjects(id),
                HttpStatus.OK);
    }

    @PostMapping("/add/member/{projectId}/{memberId}/{ownerId}")
    public ResponseEntity<ProjectDtoResponse> addMemberToProject(
            @PathVariable Long projectId, @PathVariable Long memberId,@PathVariable Long ownerId) throws AccessDeniedException {
        //Long projectId, Long memberId, Long ownerId

        return new ResponseEntity<>(
                projectServiceImpl.addMember(projectId,memberId,ownerId),
                HttpStatus.OK);
    }

    @DeleteMapping("/remove/{projectId}/{memberId}/{ownerId}")
    public ResponseEntity<ProjectDtoResponse> removeMember(
            @PathVariable Long projectId, @PathVariable Long memberId,@PathVariable Long ownerId) throws AccessDeniedException {
        return new ResponseEntity<>(
                projectServiceImpl.removeMember(projectId,memberId,ownerId),
                HttpStatus.OK);
    }










}
