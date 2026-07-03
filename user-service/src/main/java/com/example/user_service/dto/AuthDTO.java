package com.example.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthDTO (@NotBlank(message = "Insira o e-mail.")
                       @Email(message = "Digite um e-mail válido.")
                       String email,

                       @NotBlank(message = "Insira a sua senha.")
                       String password){
}
