package com.abutua.product_backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.services.CategoryService;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

}
