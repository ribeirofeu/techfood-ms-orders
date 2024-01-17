package com.fiap.techfood.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.infrastructure.repository.entity.OrderEntity;
import com.fiap.techfood.utils.ModelUtils;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrderBdRepositoryTest {

  @InjectMocks private OrderBdRepository orderBdRepository;

  @Mock private SpringOrderRepository springOrderRepository;

  @Mock private SpringProductRepository springProductRepository;

  AutoCloseable openMocks;

  @BeforeEach
  void setup() {
    openMocks = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void shouldFindOrderByIdSuccess() {
    OrderEntity orderEntity =
        OrderEntity.builder()
            .id(1L)
            .totalValue(new BigDecimal(23))
            .status(OrderStatus.CREATED)
            .createdDateTime(OffsetDateTime.now())
            .build();

    when(springOrderRepository.findById(anyLong())).thenReturn(Optional.of(orderEntity));
    Optional<Order> order = orderBdRepository.findById(1L);

    assertNotNull(order.orElse(null));
  }

  @Test
  void shouldSaveOrderSuccess() {
    OrderEntity entity = ModelUtils.createOrderEntityInstance();

    Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
    when(springOrderRepository.saveAndFlush(any())).thenReturn(entity);
    when(springProductRepository.getReferenceById(anyLong()))
        .thenReturn(ModelUtils.createProductEntityInstance());

    Order createdOrder = orderBdRepository.save(order);

    assertNotNull(createdOrder);
  }

  @Test
  void shouldUpdateOrderSuccess() {
    Order order = ModelUtils.createOrderInstance(1L, OrderStatus.CREATED);
    assertDoesNotThrow(() -> orderBdRepository.updateOrderStatus(order));
  }

  @Test
  void shouldFindAllOrderNotCompletedSuccess() {
    assertDoesNotThrow(() -> orderBdRepository.findAllNotCompleted());
  }

  @Test
  void shouldFindOrderByStatusAndTimeSuccess() {
    assertDoesNotThrow(
        () ->
            orderBdRepository.findOrdersByStatusAndTimeInterval(
                OrderStatus.IN_PREPARATION, OffsetDateTime.now(), OffsetDateTime.now()));
  }
}
