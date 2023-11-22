package com.takflow.task_manager.dto.response;

import com.takflow.task_manager.model.Task;
import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.IsActive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.List;

@Data
public class ProjectDtoResponse {
    @NotNull
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    private IsActive isActive;

    @NotEmpty
    private List<Task> tasks;

    @NotEmpty
    private List<UserProject> members;

}
