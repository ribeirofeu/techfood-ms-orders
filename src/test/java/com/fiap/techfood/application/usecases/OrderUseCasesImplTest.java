package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.application.dto.response.OrderPaymentStatusDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.gateways.OrderRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.NotificationUseCases;
import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderPaymentStatus;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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

  @Mock private NotificationUseCases notificationUseCases;

  @BeforeEach
  void setup() {
    openMocks = MockitoAnnotations.openMocks(this);
    orderUseCases = new OrderUseCasesImpl(orderRepository, productRepository, customerRepository, notificationUseCases);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void shouldCreateOrderSuccess(){
      Customer customer = ModelUtils.createCustomerInstance(1L);
      Product product = ModelUtils.createProductInstance(10L);
      Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
      OrderRequestDTO requestDTO = ModelUtils.createOrderRequestDTOInstance();

      when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
      when(productRepository.findAllByIdIn(any())).thenReturn(List.of(product));
      when(orderRepository.save(any())).thenReturn(order);

      Order result = orderUseCases.createOrder(requestDTO);
      assertEquals(OrderStatus.CREATED, result.getStatus());

      verify(customerRepository).findById(anyLong());

  }

  @Test
  void shouldThrowBusinessExceptionCreateOrder() {
    Customer customer = ModelUtils.createCustomerInstance(1L);
    Product product = ModelUtils.createProductInstance(10L);
    Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
    OrderRequestDTO requestDTO = ModelUtils.createOrderRequestDTOInstance();

    when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
    when(productRepository.findAllByIdIn(any())).thenReturn(new ArrayList<>());
    when(orderRepository.save(any())).thenReturn(order);

    assertThrows(BusinessException.class, () -> orderUseCases.createOrder(requestDTO));

    verify(customerRepository).findById(anyLong());
  }

  @Test
    void shouldUpdateOrderStatusSuccess(){
      Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
      when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
      Order orderUpdated = orderUseCases.updateOrderStatus(1L, OrderStatus.CREATED);

      assertNotNull(orderUpdated);

  }

    @Test
    void shouldUpdateOrderByStatusSuccess(){
        Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        Order orderUpdated = orderUseCases.updateOrderStatus(1L, OrderStatus.CREATED);

        assertNotNull(orderUpdated);

    }

    @Test
    void shouldFindOrderByStatusAndTimeSuccess(){
        SearchOrdersRequestDTO parameters = ModelUtils.createSearchOrdersRequestDTOInstance();
        List<Order> orders = List.of(ModelUtils.createOrderInstance(1L, OrderStatus.CREATED));
        when(orderRepository.findOrdersByStatusAndTimeInterval(any(), any(), any())).thenReturn(orders);

        List<Order> result = orderUseCases.findOrdersByStatusAndTimeInterval(parameters);

        assertNotNull(result);

    }
    @Test
    void shouldThrowBusinessExceptionInvalidRange(){
        SearchOrdersRequestDTO parameters = ModelUtils.createSearchOrdersRequestDTOInvalidRangeInstance();
        List<Order> orders = List.of(ModelUtils.createOrderInstance(1L, OrderStatus.CREATED));
        when(orderRepository.findOrdersByStatusAndTimeInterval(any(), any(), any())).thenReturn(orders);

        assertThrows(BusinessException.class, () -> orderUseCases.findOrdersByStatusAndTimeInterval(parameters));
    }

    @Test
    void shouldFindOrderNotCompletedSuccess(){

        List<Order> orders = List.of(ModelUtils.createOrderInstance(1L, OrderStatus.CREATED));
        when(orderRepository.findAllNotCompleted()).thenReturn(orders);

        List<Order> result = orderUseCases.findNotCompletedOrders();

        assertNotNull(result);

    }

    @Test
    void shouldFindOrderPaymentStatusSuccess(){

        Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        OrderPaymentStatusDTO status = orderUseCases.getOrderPaymentStatus(1L);

        assertNotNull(status);
        assertEquals(OrderPaymentStatus.PENDING, status.getStatus());

    }

    @Test
    void shouldFindOrderPaymentStatusRejectedSuccess(){

        Order order = ModelUtils.createOrderInstance(1L, OrderStatus.REJECTED);
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        OrderPaymentStatusDTO status = orderUseCases.getOrderPaymentStatus(1L);

        assertNotNull(status);
        assertEquals(OrderPaymentStatus.REJECTED, status.getStatus());

    }

    @Test
    void shouldFindOrderPaymentStatusApprovedSuccess(){

        Order order = ModelUtils.createOrderInstance(1L, OrderStatus.IN_PREPARATION);
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        OrderPaymentStatusDTO status = orderUseCases.getOrderPaymentStatus(1L);

        assertNotNull(status);
        assertEquals(OrderPaymentStatus.APPROVED, status.getStatus());

    }



}
