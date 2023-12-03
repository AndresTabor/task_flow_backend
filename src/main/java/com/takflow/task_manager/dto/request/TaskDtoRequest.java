package com.takflow.task_manager.dto.request;

import com.takflow.task_manager.model.enums.TaskState;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;


import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class TaskDtoRequest {

    @NotBlank(message = "El titulo es requerido")
    @Size(min = 1, max = 20)
    private String title;

    private String description;

    private TaskState state = TaskState.PENDING;

    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @NotNull(message = "La fecha de vencimiento de la tarea no puede esta vacía")
    @Pattern(regexp = "^[0-9]{4}(-[0-9]{2}){2} [0-9]{2}(:[0-9]{2}){2}$",
    message = "La fecha debe cumplir con el el formato yyyy-mm-dd hh:mm:ss ")
    private String dueDate;

    @NotNull(message = "Se requiere el id del proyecto")
    private Long projectId;
}
