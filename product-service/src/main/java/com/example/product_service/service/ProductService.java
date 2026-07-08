package com.example.product_service.service;

import com.example.product_service.dto.NewProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.models.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDTO createProduct(NewProductRequestDTO dto) {
        Product product = Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .category(dto.category())
                .imageUrl(dto.imageUrl())
                .build();

        Product saved =  productRepository.save(product);
        return productMapper.toDto(saved);
    }

}
