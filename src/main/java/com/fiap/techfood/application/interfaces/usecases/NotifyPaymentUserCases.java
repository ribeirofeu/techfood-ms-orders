package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.payment.PaymentStatus;

public interface NotifyPaymentUserCases {
    void execute(Long orderId, PaymentStatus paymentStatus);
}
