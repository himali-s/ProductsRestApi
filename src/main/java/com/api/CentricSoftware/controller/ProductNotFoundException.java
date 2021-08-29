package com.api.CentricSoftware.controller;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(UUID id) {
        super("Could not find product " + id);
    }
}