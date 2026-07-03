package com.example.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotBlank(message = "Informe seu nome.")
                             String name,

                             @NotBlank(message = "Informe seu e-mail.")
                             @Email(message = "Insira um e-mail válido.")
                             String email,

                             @NotBlank(message = "Informe a sua senha.")
                             @Size(min = 8, message = "Sua senha deve conter no mínimo 8 caracteres.")
                             String password) {
}
