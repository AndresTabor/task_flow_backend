package com.takflow.task_manager.config.mapper;

import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", source = "id")
    UserDtoResponse UserToDto(User user);
    @Mapping(target = "name", source = "name")
    User DtoToUser(UserDtoRequest userDto);

}
