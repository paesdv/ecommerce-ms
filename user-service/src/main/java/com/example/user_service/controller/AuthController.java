package com.example.user_service.controller;

import com.example.user_service.dto.AuthDTO;
import com.example.user_service.dto.TokenResponseDTO;
import com.example.user_service.dto.UserRequestDTO;
import com.example.user_service.services.AuthService;
import com.example.user_service.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AuthDTO dto){
        TokenResponseDTO token =  authService.login(dto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO dto){
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }





}
