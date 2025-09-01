package com.abutua.product_backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.repositories.CategoryRepository;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

}
