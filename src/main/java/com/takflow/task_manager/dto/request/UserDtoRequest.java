package com.takflow.task_manager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 50)
    private String name;

    @Email(message = "Formato de Email invalido")
    private String email;
    //Minimo 8 caracteres-Maximo 15-Al menos una letra mayúscula-Al menos una letra minucula
    //Al menos un dígito-No espacios en blanco- Al menos 1 caracter especial
    @Pattern(message = "Formato de contraseña invalido",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}")
    private String password;
    
    @NotBlank(message = "El rol es requerido")
    private String role;

}
