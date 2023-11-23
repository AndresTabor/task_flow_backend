package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;

public interface UserService {
    UserDtoResponse createUser(UserDtoRequest user);

    UserDtoResponse updateUser(UserDtoRequest partialUser, Long id);

    UserDtoResponse getUserById(Long id);

    void deleteUserById(Long id);
}
