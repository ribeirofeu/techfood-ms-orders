package com.fiap.techfood.application.usecases;

import static org.junit.jupiter.api.Assertions.*;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.gateways.OrderRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.order.Order;
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
class OrderUseCasesIT {

  private OrderUseCases orderUseCases;

  @Autowired private OrderRepository orderRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private CustomerRepository customerRepository;

  AutoCloseable openMocks;

  @BeforeEach
  void setup() {
    openMocks = MockitoAnnotations.openMocks(this);
    orderUseCases = new OrderUseCasesImpl(orderRepository, productRepository, customerRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void shouldCreateOrderSuccess() {
      OrderRequestDTO requestDTO = ModelUtils.createOrderRequestDTOInstance();
      Order order = orderUseCases.createOrder(requestDTO);
      assertNotNull(order);

  }

}
