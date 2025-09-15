package com.abutua.product_backend.dto;

import jakarta.validation.constraints.Min;

public class IntegerDTO {
    @Min(value = 1, message = "Min value = 1")
    private int id;

    public IntegerDTO() {
    }

    public IntegerDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
