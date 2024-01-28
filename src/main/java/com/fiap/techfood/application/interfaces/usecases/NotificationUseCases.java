package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.application.dto.response.PaymentDTO;
import org.springframework.http.ResponseEntity;

public interface NotificationUseCases {
    String send(PaymentDTO payment);
}
