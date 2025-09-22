package com.abutua.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.dto.ProductRequest;
import com.abutua.product_backend.dto.ProductResponse;
import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.models.Product;
import com.abutua.product_backend.repositories.CategoryRepository;
import com.abutua.product_backend.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return product;
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                                .stream()
                                .map(p -> p.toDTO())
                                .toList();
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toEntity());

        return product.toDTO();
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
     
        Product product = getById(id);

        Category category = categoryRepository.findById(productUpdate.getCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        product.setName(productUpdate.getName());
        product.setDescription(productUpdate.getDescription());
        product.setPrice(productUpdate.getPrice());
        product.setCategory(category);
        product.setPromotion(productUpdate.isPromotion());
        product.setNewProduct(productUpdate.isNewProduct());

        productRepository.save(product);
    }

    public ProductResponse getDTOById(long id) {
        Product product = getById(id);
        return product.toDTO();
    }
    
}
