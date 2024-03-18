package com.fiap.techfood.infrastructure.messaging.events;

import com.fiap.techfood.domain.commons.Event;
import com.fiap.techfood.domain.commons.EventType;
import com.fiap.techfood.domain.payment.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class ReceivedPaymentStatusEvent implements Event {
    private Long orderId;
    private Long customerId;
    private PaymentStatus paymentStatus;
    private OffsetDateTime paymentDateTime;
    private EventType eventType;
}
