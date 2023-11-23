package com.takflow.task_manager.dto.request;

import com.takflow.task_manager.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;


import java.util.List;

@Data
public class ProjectDtoRequest {
    @NonNull
    private Long ownerId;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    private List<User> members;

    public ProjectDtoRequest() {
    }
}
