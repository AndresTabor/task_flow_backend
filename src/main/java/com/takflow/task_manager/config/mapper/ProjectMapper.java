package com.takflow.task_manager.config.mapper;

import com.takflow.task_manager.dto.request.ProjectDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.UserDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "name", source = "name")
    ProjectDtoResponse projectToDto(Project project);

    @Mapping(target = "name", source = "name")
    Project dtoToProject(ProjectDtoRequest projectDtoRequest);


}
