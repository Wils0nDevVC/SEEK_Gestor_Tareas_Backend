package com.tareas.api.controller;

import com.tareas.api.dto.UserDTO;
import com.tareas.api.entity.User;
import com.tareas.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserController {

    UserService userService;
    UserController(UserService userService){
        this.userService = userService;

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> obtenerUsers() {
        List<UserDTO> users = userService.obtenerUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> obtenerUser(@PathVariable String id) {
        Optional<UserDTO> user = userService.obtenerUser(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



}
