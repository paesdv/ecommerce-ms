package com.example.user_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ChangeEmailDTO(@NotBlank(message = "O e-mail é obrigatório.")
                             @Email(message = "E-mail inválido")
                             String newEmail,

                             @NotBlank(message = "A senha é obrigatória.")
                             String password) {
}
