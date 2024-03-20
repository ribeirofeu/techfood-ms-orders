package com.fiap.techfood.infrastructure.messaging.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class CompletedProductionEvent {
    private Long orderId;
    private OffsetDateTime datetime;
    private String eventType;
}
