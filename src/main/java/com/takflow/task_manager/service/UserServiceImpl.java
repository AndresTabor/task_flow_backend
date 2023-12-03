package com.takflow.task_manager.service;

import com.takflow.task_manager.config.mapper.UserMapper;
import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.exception.EntityNotFoundException;
import com.takflow.task_manager.model.User;
import com.takflow.task_manager.model.enums.IsActive;
import com.takflow.task_manager.repository.UserRepository;
import com.takflow.task_manager.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public static final String USER_NOT_FOUND = "User not found with ID: ";


    @Override
    public UserDtoResponse createUser(UserDtoRequest user) {
        User userToRegister = UserMapper.INSTANCE.dtoToUser(user);
        User newUser = userRepository.save(userToRegister);
        return UserMapper.INSTANCE.userToDto(newUser);
    }
    @Transactional
    @Override
    public UserDtoResponse updateUser(UserDtoRequest partialUser, Long id) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + id));

        UserMapper.INSTANCE.updateUser(partialUser, userToUpdate);
        return UserMapper.INSTANCE.userToDto(userToUpdate);
    }

    @Override
    public UserDtoResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + id));

        if (user.getIsActive().equals(IsActive.DISABLED)){
            throw new EntityNotFoundException(USER_NOT_FOUND + id);
        }
        return UserMapper.INSTANCE.userToDto(user);
    }
    //Implements a logic elimination
    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND + id));
        user.setIsActive(IsActive.DISABLED);
    }


}
