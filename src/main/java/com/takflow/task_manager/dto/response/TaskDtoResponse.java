package com.takflow.task_manager.dto.response;

import com.takflow.task_manager.model.enums.TaskState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class TaskDtoResponse {
    @NotNull(message = "La tarea debe tener un Id asignado")
    private long id;

    @NotBlank(message = "El titulo es requerido")
    @Size(min = 1, max = 20)
    private String title;

    private String description = "";

    @NotNull(message = "La tarea debe tener un estado asignado")
    private TaskState state;

    @NotNull(message = "La tarea debe tener un fecha de creación asignada")
    private Timestamp createdAt;

    @NotNull(message = "La fecha de vencimiento de la tarea no puede esta vacía")
    private Timestamp dueDate;

    private MemberDto assignedMember;
}
