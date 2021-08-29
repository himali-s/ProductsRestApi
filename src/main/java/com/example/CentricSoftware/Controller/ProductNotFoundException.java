package com.example.CentricSoftware.Controller;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(UUID id) {
        super("Could not find product " + id);
    }
}