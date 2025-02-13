package com.tareas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String telefono;
}
