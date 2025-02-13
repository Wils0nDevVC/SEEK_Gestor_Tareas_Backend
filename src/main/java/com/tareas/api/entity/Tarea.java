package com.tareas.api.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tareas")
@EqualsAndHashCode(callSuper = true)
public class Tarea extends  BaseEntity{

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @JsonIgnore // Evita que el objeto user se serialice en la respuesta
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("user_id") // Hace que solo se envíe `user_id` en el request y response
    @Transient // Evita que se mapee como una columna en la BD
    private long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
        this.user = (userId != null) ? new User(userId) : null;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public enum Estado {
        POR_HACER, EN_PROGRESO, COMPLETADA
    }
}
