package com.fiap.techfood.domain.order;

import com.fiap.techfood.domain.commons.Event;
import com.fiap.techfood.domain.commons.EventType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
public class CreatedOrderEvent implements Event {
    private Long number;
    private Long customerId;
    private BigDecimal totalValue;
    private OffsetDateTime createdDateTime;

    @Override
    public EventType getEventType() {
        return EventType.CREATED_ORDER;
    }
}
