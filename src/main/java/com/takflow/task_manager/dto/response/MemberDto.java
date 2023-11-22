package com.takflow.task_manager.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberDto {
    @NotEmpty(message = "El id es requerido")
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    @Email(message = "Formato de Email invalido")
    private String email;

    public MemberDto() {
    }
}
