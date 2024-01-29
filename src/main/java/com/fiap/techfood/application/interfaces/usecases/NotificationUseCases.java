package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.application.dto.response.PaymentDTO;

public interface NotificationUseCases {
    String send(PaymentDTO payment);
}
