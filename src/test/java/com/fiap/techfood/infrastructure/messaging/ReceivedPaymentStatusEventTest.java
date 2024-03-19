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
        // Criar um objeto ReceivedPaymentStatusEvent usando o padrão Builder
        ReceivedPaymentStatusEvent event = ReceivedPaymentStatusEvent.builder()
                .orderId(123L)
                .customerId(456L)
                .paymentStatus(PaymentStatus.APPROVED)
                .paymentDateTime(OffsetDateTime.now())
                .eventType(EventType.RECEIVED_PAYMENT_STATUS)
                .build();

        // Verificar se os valores estão corretos usando os getters
        assertEquals(123L, event.getOrderId());
        assertEquals(456L, event.getCustomerId());
        assertEquals(PaymentStatus.APPROVED, event.getPaymentStatus());
        assertEquals(EventType.RECEIVED_PAYMENT_STATUS, event.getEventType());

        // Alterar os valores usando os setters
        event.setOrderId(789L);
        event.setCustomerId(987L);
        event.setPaymentStatus(PaymentStatus.REJECTED);
        event.setPaymentDateTime(OffsetDateTime.now().minusDays(1));

        // Verificar se os valores foram alterados corretamente
        assertEquals(789L, event.getOrderId());
        assertEquals(987L, event.getCustomerId());
        assertEquals(PaymentStatus.REJECTED, event.getPaymentStatus());
    }
}
