package com.fiap.techfood.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    APPROVED,
    REJECTED;
}

