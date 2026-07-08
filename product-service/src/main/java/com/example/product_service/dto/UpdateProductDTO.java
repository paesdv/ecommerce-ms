package com.example.product_service.dto;

import com.example.product_service.enums.Category;

import java.math.BigDecimal;

public record UpdateProductDTO(String name, String description, BigDecimal price, Category category, String imageUrl) {
}
