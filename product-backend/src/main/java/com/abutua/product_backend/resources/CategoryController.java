package com.abutua.product_backend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.models.Category;

@CrossOrigin
@RestController
public class CategoryController {
    
    private List<Category> categories = Arrays.asList(  new Category(1, "Produção Própria"),
                                                        new Category(2, "Nacional"),
                                                        new Category(3, "Importado"),
                                                        new Category(4, "Premium")
    );

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<Category> getCategoria(@PathVariable int id) {
    
        Category cat = categories.stream()
                                .filter(p -> p.getId() == id)
                                .findFirst()
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ResponseEntity.ok(cat);
    }
}
