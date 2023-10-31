package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.repository.UserRepository;
import com.takflow.task_manager.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDtoResponse CreateUser(UserDtoRequest user) {
        User userToRegister = UserMapper.INSTANCE.DtoToUser(user);
        User newUser = userRepository.save(userToRegister);
        return UserMapper.INSTANCE.UserToDto(newUser);
    }
}
