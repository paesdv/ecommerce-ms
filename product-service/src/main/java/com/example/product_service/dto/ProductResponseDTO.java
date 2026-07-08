package com.example.product_service.dto;

import com.example.product_service.enums.Category;

public record ProductResponseDTO(String name, String description, double price, Category category, String imageUrl) {
}
