package com.example.product_service.mapper;

import com.example.product_service.dto.NewProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.models.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(NewProductRequestDTO dto);

    ProductResponseDTO toDto(Product product);

}
