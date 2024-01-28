package com.fiap.techfood.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class PaymentDTO {

    @JsonProperty("idPedido")
    private Long id;

    @JsonProperty("valorTotal")
    private BigDecimal totalValue;

    @JsonProperty("qrCode")
    private String qrCode;

    public PaymentDTO(Long id, BigDecimal totalValue) {
        this.id = id;
        this.totalValue = totalValue;
    }
}
