package com.fiap.techfood.infrastructure.messaging;

import com.fiap.techfood.infrastructure.messaging.events.CompletedProductionEvent;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletedProductionEventTest {

    @Test
    public void testCompletedProductionEventBuilder() {
        // Arrange
        Long orderId = 1L;
        OffsetDateTime datetime = OffsetDateTime.now();
        String eventType = "COMPLETED_PRODUCTION";

        // Act
        CompletedProductionEvent event = CompletedProductionEvent.builder()
                .orderId(orderId)
                .datetime(datetime)
                .eventType(eventType)
                .build();

        // Assert
        assertNotNull(event);
        assertEquals(orderId, event.getOrderId());
        assertEquals(datetime, event.getDatetime());
        assertEquals(eventType, event.getEventType());
    }

    @Test
    public void testSetterMethods() {
        // Arrange
        Long orderId = 1L;
        OffsetDateTime datetime = OffsetDateTime.now();
        String eventType = "RECEIVED";

        Long newOrderId = 2L;
        String newEventType = "READY";

        CompletedProductionEvent event = CompletedProductionEvent.builder()
                .orderId(orderId)
                .datetime(datetime)
                .eventType(eventType)
                .build();

        // Act
        event.setOrderId(newOrderId);
        event.setDatetime(datetime);
        event.setEventType(newEventType);

        // Assert
        assertEquals(newOrderId, event.getOrderId());
        assertEquals(datetime, event.getDatetime());
        assertEquals(newEventType, event.getEventType());
    }
}
