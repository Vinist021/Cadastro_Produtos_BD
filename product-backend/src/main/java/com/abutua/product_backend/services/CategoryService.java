package com.abutua.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.dto.CategoryRequest;
import com.abutua.product_backend.dto.CategoryResponse;
import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        return category;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
         Category category = categoryRepository.save(categoryRequest.toEntity());
         return category.toDTO();
    }

    public void deleteById(int id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public void update(int id, Category categoryUpdate) {

        Category category = getById(id);

        category.setName(categoryUpdate.getName());

        categoryRepository.save(category);
    }
}
