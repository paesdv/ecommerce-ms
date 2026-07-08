package com.example.user_service.controller;

import com.example.user_service.dto.UserResponseDTO;
import com.example.user_service.dto.ChangeEmailDTO;
import com.example.user_service.dto.ChangePassDTO;
import com.example.user_service.exceptions.UserNotFoundException;
import com.example.user_service.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/by-email")
    public ResponseEntity<UserResponseDTO> findByEmail(@RequestParam @Email String email) {
        UserResponseDTO user = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/by-name")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> findByName(@RequestParam @NotBlank String name){
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PostMapping("/change-email")
    public ResponseEntity<?>changeEmail(@Valid @RequestBody ChangeEmailDTO dto, Authentication authentication){
        userService.changeEmail(authentication, dto);
        return ResponseEntity.ok("Email atualizado com sucesso!");
    }

    @PostMapping("/change-pass")
    public ResponseEntity<?>changePass(@Valid @RequestBody ChangePassDTO dto, Authentication authentication){
       userService.changePassword(authentication, dto);
       return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

}
