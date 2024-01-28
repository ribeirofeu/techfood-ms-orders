package com.fiap.techfood.domain.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techfood.application.dto.request.PaymentRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    private long id;
    private BigDecimal totalValue;

    public static Payment fromPaymentDTO(PaymentRequestDTO dto) {
        return Payment.builder()
                .id(dto.getId())
                .totalValue(dto.getTotalValue())
                .build();
    }
}
