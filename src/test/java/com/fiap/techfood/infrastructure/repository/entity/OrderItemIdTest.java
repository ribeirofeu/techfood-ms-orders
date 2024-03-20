package com.fiap.techfood.infrastructure.repository.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

 class OrderItemIdTest {
    private OrderItemId orderItemId;

    @BeforeEach
     void setUp() {
        orderItemId = new OrderItemId(1L, 2L);
    }

    @Test
     void testEquals() {
        OrderItemId sameOrderItemId = new OrderItemId(1L, 2L);
        assertEquals(orderItemId, sameOrderItemId);

        OrderItemId differentOrderItemId = new OrderItemId(3L, 4L);
        assertNotEquals(orderItemId, differentOrderItemId);
    }

    @Test
     void testHashCode() {
        OrderItemId sameOrderItemId = new OrderItemId(1L, 2L);
        assertEquals(orderItemId.hashCode(), sameOrderItemId.hashCode());

        OrderItemId differentOrderItemId = new OrderItemId(3L, 4L);
        assertNotEquals(orderItemId.hashCode(), differentOrderItemId.hashCode());
    }

    @Test
     void testOrderItemEntityBuilder() {
        // Arrange
        OrderEntity order = new OrderEntity();
        ProductEntity product = new ProductEntity();
        Integer quantity = 2;
        BigDecimal unitPrice = BigDecimal.valueOf(10.00);

        // Act
        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .build();

        // Assert
        assertNotNull(orderItemEntity);
    }

    @Test
     void testOrderItemIdBuilder() {
        // Arrange
        Long orderId = 1L;
        Long productId = 2L;

        // Act
        OrderItemId orderItemId = OrderItemId.builder().orderId(orderId).productId(productId).build();

        // Assert
        assertNotNull(orderItemId);
    }

    @Test
     void testToString() {
        // Arrange
        Long orderId = 1L;
        Long productId = 2L;
        OrderItemId orderItemId = OrderItemId.builder().orderId(orderId).productId(productId).build();

        // Act
        String orderItemIdString = orderItemId.toString();

        // Assert
        assertNotNull(orderItemIdString);
    }
}
