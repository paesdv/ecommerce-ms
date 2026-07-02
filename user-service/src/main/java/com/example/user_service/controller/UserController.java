package com.example.user_service.controller;

import com.example.user_service.dto.UserResponseDTO;
import com.example.user_service.dto.changeEmailDTO;
import com.example.user_service.dto.changePassDTO;
import com.example.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> findByEmail(@RequestParam String email){
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findByName(@RequestParam String name){
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PostMapping("/change-email")
    public ResponseEntity<?>changeEmail(@RequestBody changeEmailDTO dto, Authentication authentication){
        userService.changeEmail(authentication, dto);
        return ResponseEntity.ok("Email atualizado com sucesso!");
    }

    @PostMapping("/change-pass")
    public ResponseEntity<?>changePass(@RequestBody changePassDTO dto, Authentication authentication){
       userService.changePassword(authentication, dto);
       return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

}
