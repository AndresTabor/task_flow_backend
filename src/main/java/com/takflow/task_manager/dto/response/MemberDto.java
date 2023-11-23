package com.takflow.task_manager.dto.response;

import com.takflow.task_manager.model.UserProject;
import com.takflow.task_manager.model.enums.IsActive;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class MemberDto {
    @NotEmpty(message = "El id es requerido")
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    @Email(message = "Formato de Email invalido")
    private String email;

    @NotNull
    private IsActive isActive;


}
