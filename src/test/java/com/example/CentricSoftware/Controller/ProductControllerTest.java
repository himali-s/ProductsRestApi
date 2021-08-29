package com.example.CentricSoftware.Controller;

import com.example.CentricSoftware.Data.Product;
import com.example.CentricSoftware.Data.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @SpyBean
    private ProductRepository productRepository;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products").param("page","0").
                param("size","4"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.size()", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name", Matchers.is("Necklace")));
        ;
    }

    @Test
    void testPagination() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products").param("page","0").
                param("size","2"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(3)));



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