package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {
  private MockMvc mockMvc;

  @Mock private CustomerUseCases customerUseCases;

  AutoCloseable mock;

  @BeforeEach
  void setup() {
    mock = MockitoAnnotations.openMocks(this);
    CustomerController customerController = new CustomerController(customerUseCases);
    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  @Test
  void shouldCallCreateCustomerSuccess() throws Exception {

    CustomerRequestDTO requestDTO =
        new CustomerRequestDTO("Cliente Teste", "34534534569", "email@teste.com.br");
    mockMvc
        .perform(
            post("/customers")
                .content(ModelUtils.asJsonString(requestDTO))
                .contentType("application/json"))
        .andExpect(status().isCreated());
  }

    @Test
    void shouldCallGetCustomerSuccess() throws Exception {
        mockMvc
                .perform(
                        get("/customers/34534567830"))
                .andExpect(status().isOk());
    }

}
