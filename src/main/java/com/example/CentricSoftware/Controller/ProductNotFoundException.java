package com.example.CentricSoftware.Controller;

public class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}