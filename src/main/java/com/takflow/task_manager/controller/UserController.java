package com.takflow.task_manager.controller;

import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;





@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> createUser(@Valid @RequestBody UserDtoRequest user){
        return new ResponseEntity<>(
                userService.createUser(user),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(
                userService.getUserById(id),
                HttpStatus.OK
        );

    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(
            @RequestBody UserDtoRequest partialUser, @PathVariable Long id){
        return new ResponseEntity<>(
                userService.updateUser(partialUser, id),
                HttpStatus.OK
        );
    }

}
