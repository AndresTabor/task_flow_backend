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

    @Pattern(regexp = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]",
    message = "La fecha debe cumplir con el el formato yyyy-mm-dd hh:mm:ss ")
    private String dueDate;
}
