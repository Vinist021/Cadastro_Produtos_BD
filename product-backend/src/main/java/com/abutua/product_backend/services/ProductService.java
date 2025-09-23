package com.abutua.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.dto.ProductRequest;
import com.abutua.product_backend.dto.ProductResponse;
import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.models.Product;
import com.abutua.product_backend.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return product;
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                                .stream()
                                .map(p -> p.toDTO())
                                .toList();
    }

    public ProductResponse save(ProductRequest productRequest) {
        try{
            Product product = productRepository.save(productRequest.toEntity());
            return product.toDTO();
        } catch(DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }
    }

    public void deleteById(long id) {
        try{
            Product product = getById(id);
            productRepository.delete(product);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    public void update(long id, ProductRequest productUpdate) {
        
        try{
            Product product = productRepository.getReferenceById(id);

            Category category = new Category(productUpdate.getCategory().getId());

            product.setName(productUpdate.getName());
            product.setDescription(productUpdate.getDescription());
            product.setPrice(productUpdate.getPrice());
            product.setCategory(category);
            product.setPromotion(productUpdate.isPromotion());
            product.setNewProduct(productUpdate.isNewProduct());

            productRepository.save(product);
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException("Product not found");
        } catch(DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }

    }
}
