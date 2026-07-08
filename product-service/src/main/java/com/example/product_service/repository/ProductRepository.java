package com.example.product_service.repository;

import com.example.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByNameContainingIgnoreCase(String name);

}
