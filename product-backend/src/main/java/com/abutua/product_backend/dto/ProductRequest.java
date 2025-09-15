package com.abutua.product_backend.dto;

import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.models.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequest {
    
    private Long id;
    
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 255, message = "Name length min=5 and max=255")
    private String name;

    @NotBlank(message = "Description can not be blank")
    @Size(min = 3, max = 1024, message = "Name length min=5 and max=1024")
    private String description;

    @Min(value = 0, message = "Price min value=0")
    private double price;

    @NotNull
    @Valid
    private IntegerDTO category;
    
    private boolean promotion;
    private boolean newProduct;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public IntegerDTO getCategory() {
        return category;
    }
    public void setCategory(IntegerDTO category) {
        this.category = category;
    }
    public boolean isPromotion() {
        return promotion;
    }
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
    public boolean isNewProduct() {
        return newProduct;
    }
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
    public Product toEntity() {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setNewProduct(newProduct);
        product.setPromotion(promotion);
        product.setPrice(price);
        product.setCategory(new Category(category.getId()));

        return product;

    }
}
