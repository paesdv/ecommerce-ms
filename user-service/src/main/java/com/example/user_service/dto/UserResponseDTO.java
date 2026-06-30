package com.example.user_service.dto;

import com.example.user_service.enums.Role;

import java.time.LocalDate;

public record UserResponseDTO(String name, String email, Role role, LocalDate createdAt) {
}
