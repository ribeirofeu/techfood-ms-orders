package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.order.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class OrderEntityTest {

    @Test
     void testOrderEntityBuilder() {
        // Arrange
        Long orderId = 1L;
        CustomerEntity customer = new CustomerEntity();
        BigDecimal totalValue = BigDecimal.valueOf(100.00);
        OrderStatus status = OrderStatus.RECEIVED;
        OffsetDateTime createdDateTime = OffsetDateTime.now();
        OffsetDateTime receivedDateTime = OffsetDateTime.now().plusHours(1);
        String notes = "Test notes";
        String qrCode = "Test QR code";

        // Act
        OrderEntity orderEntity = OrderEntity.builder()
                .id(orderId)
                .customer(customer)
                .totalValue(totalValue)
                .status(status)
                .createdDateTime(createdDateTime)
                .receivedDateTime(receivedDateTime)
                .notes(notes)
                .qrCode(qrCode)
                .items(Collections.emptyList())
                .build();

        // Assert
        assertNotNull(orderEntity);
        assertEquals(orderId, orderEntity.getId());
        assertEquals(customer, orderEntity.getCustomer());
        assertEquals(totalValue, orderEntity.getTotalValue());
        assertEquals(status, orderEntity.getStatus());
        assertEquals(createdDateTime, orderEntity.getCreatedDateTime());
        assertEquals(receivedDateTime, orderEntity.getReceivedDateTime());
        assertEquals(notes, orderEntity.getNotes());
        assertEquals(qrCode, orderEntity.getQrCode());
    }
}
