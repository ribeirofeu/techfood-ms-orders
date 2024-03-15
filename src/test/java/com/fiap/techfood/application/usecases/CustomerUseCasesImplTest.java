package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerUseCasesImplTest {

  private CustomerUseCases customerUseCases;

  @Mock private CustomerRepository customerRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    customerUseCases = new CustomerUseCasesImpl(customerRepository);
  }

  @Test
  void shouldCreateCustomerSuccess(){
      CustomerRequestDTO requestDTO = new CustomerRequestDTO("Cliente Teste", "34534534569", "email@teste.com.br", true);
      assertDoesNotThrow(() -> customerUseCases.createCustomer(requestDTO));
      verify(customerRepository).save(any());
  }

}
