package com.fiap.techfood.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentDTO {

    @JsonProperty("idPedido")
    private final Long id;

    @JsonProperty("valorTotal")
    private final BigDecimal totalValue;

    @JsonProperty("qrCode")
    private String qrCode;

    public PaymentDTO(Long id, BigDecimal totalValue) {
        this.id = id;
        this.totalValue = totalValue;
    }
}
