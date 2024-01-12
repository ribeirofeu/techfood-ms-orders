package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.gateways.OrderRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderUseCasesImplTest {
  private OrderUseCases orderUseCases;

  AutoCloseable openMocks;

  @Mock private OrderRepository orderRepository;

  @Mock private CustomerRepository customerRepository;

  @Mock private ProductRepository productRepository;

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
  void shouldCreateOrderSuccess(){
      Customer customer = ModelUtils.createCustomerInstance(1L);
      Product product = ModelUtils.createProductInstance(1L);
      Order order = ModelUtils.createOrderInstance(1L);
      OrderRequestDTO requestDTO = ModelUtils.createOrderRequestDTOInstance();

      when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
      when(productRepository.findAllByIdIn(List.of(1L))).thenReturn(List.of(product));
      when(orderRepository.save(any())).thenReturn(order);

      Order result = orderUseCases.createOrder(requestDTO);
      assertEquals(OrderStatus.CREATED, result.getStatus());

      verify(customerRepository).findById(anyLong());

  }

}
