package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {

  private MockMvc mockMvc;

  @Mock private OrderUseCases orderUseCases;

  @InjectMocks private OrderController orderController;

  AutoCloseable mock;

  @BeforeEach
  void setup() {
    mock = MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  @Test
  void shouldCallCreateOrderSuccess() throws Exception {
    OrderRequestDTO requestDTO = ModelUtils.createOrderRequestDTOInstance();
    mockMvc
        .perform(
            post("/orders")
                .content(ModelUtils.asJsonString(requestDTO))
                .contentType("application/json"))
        .andExpect(status().isCreated());
  }
}
