package com.fiap.techfood.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductUseCases productUseCases;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        ProductController productController = new ProductController(productUseCases);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }

  @Test
  void shouldCreateProductSuccess() throws Exception {

    ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance();
    mockMvc
        .perform(post("/products").content(ModelUtils.asJsonString(requestDTO)).contentType("application/json"))
        .andExpect(status().isCreated());
  }
}
