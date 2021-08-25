package com.example.CentricSoftware.Controller;

import com.example.CentricSoftware.Data.Product;
import com.example.CentricSoftware.Data.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private  final ProductRepository productRepository;
    ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping("/products")
    Page<Product> all(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {

//        return productRepository.findAll();
        //by default sorted by id
        //by default page is 0

          return productRepository.findAll(
                  PageRequest.of(
                          page.orElse(0),
                          5,
                          Sort.Direction.ASC, sortBy.orElse("id")
            )
        );
    }


    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) {
        System.out.println(productRepository);

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }



}
