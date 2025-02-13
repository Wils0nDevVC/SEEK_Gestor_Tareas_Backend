package com.tareas.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")
    @NotNull()
    @Email()
    private String email;

    @NotBlank(message = "El password apellido es obligatorio")
    @NotNull()
    @Size(min = 8, max = 32, message = "El password debe tener entre 8 y 32 caracteres")
    private String password;
}
