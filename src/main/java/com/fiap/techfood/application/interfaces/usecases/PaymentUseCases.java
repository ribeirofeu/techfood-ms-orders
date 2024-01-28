package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.application.dto.request.PaymentRequestDTO;

public interface PaymentUseCases {
    Boolean generatePaymentQRCode(PaymentRequestDTO request);
}
