package com.example.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePassDTO(@NotBlank(message = "Digite a nova senha.")
                            String newPassword,

                            @NotBlank(message = "Digite a senha atual.")
                            @Size(min = 8, message = "A nova senha deve ter no mínimo 8 caracteres.")
                            String currentPassword) {
}
