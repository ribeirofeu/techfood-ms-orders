package com.fiap.techfood.infrastructure.services;

import com.fiap.techfood.application.dto.request.PaymentRequestDTO;
import com.fiap.techfood.application.dto.response.PaymentDTO;
import com.fiap.techfood.application.interfaces.usecases.NotificationUseCases;
import com.fiap.techfood.domain.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class NotificationUseCasesImpl implements NotificationUseCases {
    private final RestTemplate restTemplate;

    @Value("${production.url}")
    private String productionUrl;

    public NotificationUseCasesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String send(PaymentDTO payment) throws NullPointerException {
        return request(payment)
                .getBody()
                .getQrCode();
    }

    private ResponseEntity<PaymentDTO> request(PaymentDTO dto) {
        HttpEntity<PaymentDTO> requestEntity = new HttpEntity<>(dto);

        return restTemplate.postForEntity(
                productionUrl,
                requestEntity,
                PaymentDTO.class
        );
    }
}
