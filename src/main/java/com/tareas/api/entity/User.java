package com.tareas.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@ToString //Lombok genera automanticamente el metodo toString
@EqualsAndHashCode //Genera automanticamente métodos equals() y hashCode()
public class User  extends  BaseEntity implements UserDetails {


    //Esto es de la librería Loombok, que nos simplifca los getters y setters
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "primer_apellido",nullable = false)
    private String primer_apellido;
    @Column(name = "segundo_apellido",nullable = false)
    private String segundo_apellido;
    @Column(name = "email",nullable = false)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaNac",nullable = false)
    private Date fechaNac;

    public User(long id) {
        setId(id);
    }

    public User(long id, String nombre, String primer_apellido, String segundo_apellido, String email, String password, String telefono, Date fechaNac) {
        setId(id);
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
