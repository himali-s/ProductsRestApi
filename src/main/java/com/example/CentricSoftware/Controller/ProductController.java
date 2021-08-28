package com.example.CentricSoftware.Controller;

import com.example.CentricSoftware.Data.Product;
import com.example.CentricSoftware.Data.ProductRepository;
import com.example.CentricSoftware.Service.ProductService;
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

import static org.springframework.http.ResponseEntity.status;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private ProductService productService;
    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//    @GetMapping("/v1/products")
//    public ResponseEntity<List<Product>> getAll(){
//        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
//    }

    @GetMapping("/v1/products")
    public ResponseEntity<Map<String,Object>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        System.out.println("Inside get all");
        try {
            List<Product> products;
            System.out.println("Before Page request");
            Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"createdAt"));
            System.out.println(paging);
            System.out.println("After Page Request");
            Page<Product> pageTuts;
            if (category == null) {
                System.out.println("Before find all");
                pageTuts = productRepository.findAll(paging);
                System.out.println("After Findall"+pageTuts);

            } else {
                pageTuts = productRepository.findByCategory(category, paging);
            }
            products = pageTuts.getContent();
            System.out.println(products);
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("products",products);
            responseMap.put("currentPage",pageTuts.getNumber());
            responseMap.put("totalProducts", pageTuts.getTotalElements());
            responseMap.put("totalPages", pageTuts.getTotalPages());
            responseMap.put("max_entries", size);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error" +e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    Product one(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
//    public List<ResponseEntity> getAll(){
////        return productRepository.findAll().stream().map(postMapper:)
//    }


    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
}
