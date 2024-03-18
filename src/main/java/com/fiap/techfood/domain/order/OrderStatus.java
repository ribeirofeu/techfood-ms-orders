package com.fiap.techfood.domain.order;

import com.fiap.techfood.domain.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED,
    RECEIVED,
    COMPLETED,
    REJECTED;

    public static OrderStatus getByPaymentStatus(PaymentStatus paymentStatus) {
        Map<PaymentStatus, OrderStatus> mapPaymentStatusToOrderStatus = Map.of(
                PaymentStatus.APPROVED, OrderStatus.RECEIVED,
                PaymentStatus.REJECTED, OrderStatus.REJECTED
        );

        return  mapPaymentStatusToOrderStatus.get(paymentStatus);
    }
}