package com.fiap.techfood.infrastructure.repository.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemIdTest {
    private OrderItemId orderItemId;

    @BeforeEach
    public void setUp() {
        orderItemId = new OrderItemId(1L, 2L);
    }

    @Test
    public void testEquals() {
        OrderItemId sameOrderItemId = new OrderItemId(1L, 2L);
        assertEquals(orderItemId, sameOrderItemId);

        OrderItemId differentOrderItemId = new OrderItemId(3L, 4L);
        assertNotEquals(orderItemId, differentOrderItemId);
    }

    @Test
    public void testHashCode() {
        OrderItemId sameOrderItemId = new OrderItemId(1L, 2L);
        assertEquals(orderItemId.hashCode(), sameOrderItemId.hashCode());

        OrderItemId differentOrderItemId = new OrderItemId(3L, 4L);
        assertNotEquals(orderItemId.hashCode(), differentOrderItemId.hashCode());
    }
}
