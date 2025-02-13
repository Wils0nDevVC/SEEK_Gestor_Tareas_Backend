package com.tareas.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tareas.api.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Validated
public class UserDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @NotNull
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El primer apellido debe tener entre 2 y 50 caracteres")
    @JsonProperty("primer_apellido")
    private String primerApellido;

    @Size(max = 50, message = "El segundo apellido no debe exceder los 50 caracteres")
    @JsonProperty("segundo_apellido")
    private String segundoApellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Size(max = 15, message = "El teléfono no debe exceder los 15 caracteres")
    private String telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private Date fechaNac;



    public UserDTO(User user) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.primerApellido = user.getPrimer_apellido();
        this.segundoApellido = user.getSegundo_apellido();
        this.email = user.getEmail();
        this.telefono = user.getTelefono();
        this.fechaNac = user.getFechaNac();
    }
}
