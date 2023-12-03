package com.takflow.task_manager.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignTaskDto {
    @NotNull(message = "El id del proyecto es requerido")
    private Long projectId;
    @NotNull(message = "El id de la tarea es requerida")
    private Long taskId;
    @NotNull(message = "El id del miembro del proyecto  es requerido")
    private Long memberId;

}
