package com.api.CentricSoftware.controller;

import com.api.CentricSoftware.data.Product;
import com.api.CentricSoftware.data.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/v1/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Product> products;
            Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<Product> pageTuts;
            if (category == null) {
                pageTuts = productRepository.findAll(paging);

            } else {
                pageTuts = productRepository.findByCategory(category, paging);
            }
            products = pageTuts.getContent();
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("products", products);
            responseMap.put("currentPage", pageTuts.getNumber());
            responseMap.put("totalProducts", pageTuts.getTotalElements());
            responseMap.put("totalPages", pageTuts.getTotalPages());
            responseMap.put("max_entries", size);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/products/{id}")
    Product one(@PathVariable UUID id) throws  Exception{
        return productRepository.findByUuid(id);
    }

    @PostMapping("/v1/products")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
}
