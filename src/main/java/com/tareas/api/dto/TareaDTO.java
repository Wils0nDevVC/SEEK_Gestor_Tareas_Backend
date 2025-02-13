package com.tareas.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tareas.api.entity.Tarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TareaDTO {

    long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;

    @Size(min = 3, max = 200, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private Estado estado;

    @NotNull(message = "El userId es obligatorio")
    @JsonProperty("user_id") // Para que Spring lo entienda al recibir JSON
    private Long userId;

    public enum Estado {
        POR_HACER, EN_PROGRESO, COMPLETADA
    }
}
