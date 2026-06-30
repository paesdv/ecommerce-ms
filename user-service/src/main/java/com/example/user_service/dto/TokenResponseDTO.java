package com.example.user_service.dto;

public record TokenResponseDTO (String token, long expires_in) {
}
