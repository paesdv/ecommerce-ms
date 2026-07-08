package com.example.product_service.mapper;

import com.example.product_service.dto.NewProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.dto.UpdateProductDTO;
import com.example.product_service.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(NewProductRequestDTO dto);

    ProductResponseDTO toDto(Product product);

    void updateEntityFromDto(UpdateProductDTO dto, @MappingTarget Product product);
}
