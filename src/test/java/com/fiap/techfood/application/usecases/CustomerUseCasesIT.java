package com.fiap.techfood.application.usecases;

import static org.junit.jupiter.api.Assertions.*;

import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.utils.ModelUtils;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class CustomerUseCasesIT {

  private CustomerUseCases customerUseCases;

  @Autowired private CustomerRepository customerRepository;

  AutoCloseable openMocks;

  @BeforeEach
  void setup() {
    openMocks = MockitoAnnotations.openMocks(this);
    customerUseCases = new CustomerUseCasesImpl(customerRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void shouldCreateCustomerSuccess() {
      CustomerRequestDTO requestDTO = new CustomerRequestDTO("Teste", "3435678754", "email@teste.com.br");
      Long customerId = customerUseCases.createCustomer(requestDTO);

      assertEquals(1L, customerId);
  }

    @Test
    void shouldFindCustomerByCPFSuccess() {
        Customer customer = customerUseCases.findCustomerByCpf("34534567840");
        assertEquals(10L, customer.getId());
    }





}
