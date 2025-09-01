package com.abutua.product_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abutua.product_backend.models.Product;

public interface ProductRepository extends  JpaRepository<Product, Integer> {
    
}
