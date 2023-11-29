package com.takflow.task_manager.config.mapper;

import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "title", source = "title")
    TaskDtoResponse taskToDto(Task task);

    @Mapping(target = "title", source = "title")
    Task dtoToTask(TaskDtoRequest taskDtoRequest);

}
