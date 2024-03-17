package com.fiap.techfood.domain.order;

import com.fiap.techfood.domain.commons.Message;
import com.fiap.techfood.domain.commons.MessageType;
import com.fiap.techfood.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
public class CreatedOrder implements Message {
    private Long number;
    private Long customerId;
    private BigDecimal totalValue;
    private OffsetDateTime createdDateTime;

    @Override
    public MessageType getMessageType() {
        return MessageType.CREATED_ORDER;
    }
}
