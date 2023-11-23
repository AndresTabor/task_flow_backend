package com.takflow.task_manager.config.mapper;

import com.takflow.task_manager.dto.request.UserDtoRequest;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "name")
    UserDtoResponse userToDto(User user);

    @Mapping(target = "name", source = "name")
    User dtoToUser(UserDtoRequest userDto);

    @Mapping(target = "name", source = "name")
    User dtoToUser(UserDtoResponse userDtoResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    void updateUser(UserDtoRequest userDto, @MappingTarget User user);
}

