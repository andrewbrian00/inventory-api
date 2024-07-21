package com.cajap.app.controllers;

import com.cajap.app.TestDataUtil;
import com.cajap.app.domain.dto.ProductDto;
import com.cajap.app.domain.entities.ProductEntity;
import com.cajap.app.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ProductService productService;

    @Autowired
    public ProductControllerIntegrationTests(MockMvc mockMvc, ProductService productService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.productService = productService;
    }

    @Test
    public void testThatCreateProductSuccessfullyReturnsHttp201Created() throws Exception {

        ProductEntity testProductA = TestDataUtil.createTestProductA();
        testProductA.setId(null);
        String productJson = objectMapper.writeValueAsString(testProductA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.partNumber").value("10002")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("NSK Bearing")
        );

    }

    @Test
    public void testThatListProductsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateProductReturnsHttpStatus200ok() throws Exception {
        ProductEntity testProductEntityA = TestDataUtil.createTestProductA();
        productService.createUpdate(testProductEntityA);

        ProductDto testProductA = TestDataUtil.createTestProductDto();
        testProductA.setAmount(1000d);
        String productJson = objectMapper.writeValueAsString(testProductA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/products/" + testProductEntityA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }
}
