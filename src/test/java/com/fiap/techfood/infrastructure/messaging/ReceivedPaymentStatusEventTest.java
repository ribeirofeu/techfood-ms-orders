package com.fiap.techfood.infrastructure.messaging;

import com.fiap.techfood.domain.commons.EventType;
import com.fiap.techfood.domain.payment.PaymentStatus;
import com.fiap.techfood.infrastructure.messaging.events.ReceivedPaymentStatusEvent;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceivedPaymentStatusEventTest {


    @Test
    public void testGetterAndSetter() {
       // Arrange and Act
        ReceivedPaymentStatusEvent event = ReceivedPaymentStatusEvent.builder()
                .orderId(123L)
                .customerId(456L)
                .paymentStatus(PaymentStatus.APPROVED)
                .paymentDateTime(OffsetDateTime.now())
                .eventType(EventType.RECEIVED_PAYMENT_STATUS)
                .build();

        // Assert
        assertEquals(123L, event.getOrderId());
        assertEquals(456L, event.getCustomerId());
        assertEquals(PaymentStatus.APPROVED, event.getPaymentStatus());
        assertEquals(EventType.RECEIVED_PAYMENT_STATUS, event.getEventType());

        // Act
        event.setOrderId(789L);
        event.setCustomerId(987L);
        event.setPaymentStatus(PaymentStatus.REJECTED);
        event.setPaymentDateTime(OffsetDateTime.now().minusDays(1));

        // Assert
        assertEquals(789L, event.getOrderId());
        assertEquals(987L, event.getCustomerId());
        assertEquals(PaymentStatus.REJECTED, event.getPaymentStatus());
    }
}
