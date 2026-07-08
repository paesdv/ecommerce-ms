package com.example.product_service.service;

import com.example.product_service.dto.NewProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.dto.UpdateProductDTO;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.models.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public ProductResponseDTO updateProduct(UUID id, UpdateProductDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        productMapper.updateEntityFromDto(dto, product);

        Product updated = productRepository.save(product);

        return productMapper.toDto(updated);
    }

    public void deleteProduct(UUID id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }

        productRepository.deleteById(id);
    }

    public ProductResponseDTO getProduct(UUID id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return productMapper.toDto(product);
    }

    public List<ProductResponseDTO> getByName(String name){
        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(name);
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    public List<ProductResponseDTO> findAll(){
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }



}
