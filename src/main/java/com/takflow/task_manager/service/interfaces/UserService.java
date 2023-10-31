package com.takflow.task_manager.service.interfaces;

import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.User;

public interface UserService {
    UserDtoResponse CreateUser(UserDtoRequest user);
}
