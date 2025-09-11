package com.abutua.product_backend.dto;

import com.abutua.product_backend.models.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 255, message = "Name length min=5 and max=255")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return new Category(name);
    }

}
