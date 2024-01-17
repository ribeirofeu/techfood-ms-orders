package com.fiap.techfood.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductUseCases productUseCases;

  AutoCloseable mock;

  @Test
  void shouldCreateProductSuccess() throws Exception {

    ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance(2L);
    mockMvc
        .perform(
            post("/products")
                .content(ModelUtils.asJsonString(requestDTO))
                .contentType("application/json"))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldGetProductByIdSuccess() throws Exception {
    mockMvc.perform(get("/products/1")).andExpect(status().isOk());
  }

  @Test
  void shouldGetAllProductSuccess() throws Exception {
    mockMvc.perform(get("/products")).andExpect(status().isOk());
  }

  @Test
  void shouldDeleteProductSuccess() throws Exception {
    mockMvc.perform(delete("/products/1")).andExpect(status().isNoContent());
  }

  @Test
  void shouldUpdateProductSuccess() throws Exception {

    ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance(2L);
    mockMvc
        .perform(
            put("/products/1")
                .content(ModelUtils.asJsonString(requestDTO))
                .contentType("application/json"))
        .andExpect(status().isOk());
  }

  @Test
  void shouldThrowBusinessExceptionCreateProduct() throws Exception {

    ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance(2L);

    when(productUseCases.createProduct(any()))
        .thenThrow(new BusinessException("Invalid Request", HttpStatusCodes.BAD_REQUEST));

    mockMvc
        .perform(
            post("/products")
                .content(ModelUtils.asJsonString(requestDTO))
                .contentType("application/json"))
        .andExpect(status().isBadRequest());
  }
}
