package com.example.product_service.dto;

import com.example.product_service.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

import java.math.BigDecimal;

public record NewProductRequestDTO(@NotBlank(message = "O campo nome é obrigatório") String name,
                                   @NotBlank @Size(min = 30, message = "A descrição deve ter no mínimo 30 caracteres") String description,
                                   @NotBlank(message = "O campo preço é obrigatório") BigDecimal price,
                                   @NotBlank(message = "O campo categoria é obrigatório") Category category,
                                   @NotBlank(message = "Adicione uma imagem do produto") String imageUrl
) {
}
