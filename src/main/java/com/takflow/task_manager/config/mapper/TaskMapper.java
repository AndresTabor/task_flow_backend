package com.takflow.task_manager.config.mapper;

import com.takflow.task_manager.dto.request.TaskDtoRequest;
import com.takflow.task_manager.dto.response.ProjectDtoResponse;
import com.takflow.task_manager.dto.response.TaskDtoResponse;
import com.takflow.task_manager.model.Project;
import com.takflow.task_manager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.expression.ParseException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "title", source = "title")
    TaskDtoResponse taskToDto(Task task);

    @Mappings({
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "dueDate", expression = "java(stringToTimestamp(taskDtoRequest.getDueDate()))")
    })
    Task dtoToTask(TaskDtoRequest taskDtoRequest);

    default Timestamp stringToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException | java.text.ParseException e) {
            throw new IllegalArgumentException("No se pudo convertir el String a Timestamp: " + e.getMessage());
        }
    }

}
