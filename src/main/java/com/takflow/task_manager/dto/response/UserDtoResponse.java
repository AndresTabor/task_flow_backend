package com.takflow.task_manager.dto.response;

import com.takflow.task_manager.model.UserProject;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
public class UserDtoResponse {

    @NotEmpty(message = "El id es requerido")
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    @Email(message = "Formato de Email invalido")
    private String email;

    @NotBlank(message = "El rol es requerido")
    private String role;

    private List<UserProject> projects;


}
