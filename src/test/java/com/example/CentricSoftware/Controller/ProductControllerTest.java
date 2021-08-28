package com.example.CentricSoftware.Controller;

import com.example.CentricSoftware.Data.Product;
import com.example.CentricSoftware.Data.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest(controllers = ProductController.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @SpyBean
    private ProductRepository productRepository;
//    @SpyBean
//    JpaRepository jpaRepository;
//    @SpyBean
//    Page page;
//    @SpyBean
//    Pageable pageable;
    @Autowired
    private MockMvc mockMvc;
    static List<Product> productList;
    static Page<Product> mockPage;

    @BeforeClass
    public static void init(){


    }
    @Test
    @DisplayName("Should List all the products")
    void getAllProducts()  throws  Exception{

//        Product mockProduct = new Product("a", "Red hugo boss shirt", "Gucci", Arrays.asList("red","shirt"), "apperal");
//        Product mockProduct1 = new Product("b", "White hugo boss shirt", "LV", Arrays.asList("gold"), "jewellary");
//        Product mockProduct2 = new Product("c", "tshirt", "LV", Arrays.asList("red"), "apperal");
//        Product mockProduct3 = new Product("d", "pant", "LV", Arrays.asList("blue"), "apperal");
//
//        productRepository.save(mockProduct);
//        productRepository.save(mockProduct1);
//        productRepository.save(mockProduct2);
//        productRepository.save(mockProduct3);
//        List<Product>  productList = Arrays.asList(mockProduct,mockProduct1,mockProduct2,mockProduct3);
//        System.out.println(productList);
        //HashMap<String, Object> mockMap = new HashMap<>();
        /*mockMap.put("products",productRepository.findAll());
        mockMap.put("currentPage",0);
        mockMap.put("totalProducts", 1);
        mockMap.put("totalPages", 1);
        mockMap.put("max_entries", 1);*/
//        Page<Product> mockPage = new PageImpl(productList);
//        Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
//        Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products").param("page","0").
                param("size","4"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.size()", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name", Matchers.is("White Shirt")));
        ;
    }

    @Test
    void testPagination() throws Exception {
//        Product mockProduct = new Product("White Shirt", "Red hugo boss shirt", "Gucci", Arrays.asList("red","shirt"), "apperal");
//        Product mockProduct1 = new Product("Red Shirt", "White hugo boss shirt", "LV", Arrays.asList("gold"), "jewellary");
//        Product mockProduct2 = new Product("Tshirt", "tshirt", "LV", Arrays.asList("red"), "apperal");
//        Product mockProduct3 = new Product("Pant", "pant", "LV", Arrays.asList("blue"), "apperal");
//
//        productRepository.save(mockProduct);
//        productRepository.save(mockProduct1);
//        productRepository.save(mockProduct2);
//        productRepository.save(mockProduct3);
//        List<Product>  productList = Arrays.asList(mockProduct,mockProduct1,mockProduct2,mockProduct3);
//        System.out.println(productList);
//        Page<Product> mockPage = new PageImpl(productList);
//        ProductRepository prodSpy = Mockito.spy(productRepository);
        //Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
       // Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products").param("page","0").
                param("size","2"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(2)));



    }
    @Test
    void testSearchByCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products").param("page","0").
                param("size","4").param("category","apperal"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].category",Matchers.is("apperal")));


    }
    @Test
    public void testNewProduct() throws Exception {
        Product mockProduct = new Product("Yellow Shirt", "Yellow shirt for men", "Gucci", Arrays.asList("yellow","shirt","men"), "apperal");

        MockHttpServletRequestBuilder mockRequest;
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(mockProduct);

        mockRequest = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonStr);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Yellow Shirt")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Yellow shirt for men")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand", Matchers.is("Gucci")))
        ;
    }

}