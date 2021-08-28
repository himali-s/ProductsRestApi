package com.example.CentricSoftware.Service;

import com.example.CentricSoftware.Data.Product;
import com.example.CentricSoftware.Data.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class ProductService {
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
