package com.tareas.api.controller;

import com.tareas.api.dto.AuthResponse;
import com.tareas.api.dto.LoginRequest;
import com.tareas.api.dto.ResponseCustom;
import com.tareas.api.dto.UserDTO;
import com.tareas.api.entity.User;
import com.tareas.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Validated
@Tag(name = "Auth", description = "API de autenticación")
public class AuthController {

    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,  UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseCustom<Void>> registerUser(@Valid @RequestBody UserDTO request) {
        UserDTO newUser = userService.registerUser(request);
        return ResponseEntity.ok(ResponseCustom.success(null, "Usuario registrado correctamente"));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            var resp = this.userService.login(loginRequest);
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Error-Message", "Credenciales inválidas")
                    .build();
        }
    }

}
