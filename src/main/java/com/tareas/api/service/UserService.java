package com.tareas.api.service;

import com.tareas.api.dto.AuthResponse;
import com.tareas.api.dto.LoginRequest;
import com.tareas.api.dto.UserDTO;
import com.tareas.api.entity.User;
import com.tareas.api.repository.UserRepository;
import com.tareas.api.util.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtils jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public List<UserDTO> obtenerUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> obtenerUser(String id) {
        return userRepository.findById(id).map(UserDTO::new);
    }

    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User user = new User();
        user.setNombre(userDTO.getNombre());
        user.setPrimer_apellido(userDTO.getPrimerApellido());
        user.setSegundo_apellido(userDTO.getSegundoApellido());
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());
        user.setFechaNac(userDTO.getFechaNac());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encriptar la contraseña

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user.getUsername());

            return new AuthResponse(
                    token,
                    user.getId(),
                    user.getNombre(),
                    user.getPrimer_apellido(),
                    user.getSegundo_apellido(),
                    user.getEmail(),
                    user.getTelefono()
            );

        } catch (Exception e) {
            throw new RuntimeException("Error al autenticar usuario", e);
        }
    }
}
