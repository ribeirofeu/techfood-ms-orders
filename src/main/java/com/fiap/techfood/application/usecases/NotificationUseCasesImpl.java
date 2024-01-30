package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.response.PaymentDTO;
import com.fiap.techfood.application.interfaces.usecases.NotificationUseCases;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class NotificationUseCasesImpl implements NotificationUseCases {
    private final RestTemplate restTemplate;

    @Value("${production.url}")
    private String productionUrl;

    public NotificationUseCasesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String send(PaymentDTO payment) throws NullPointerException {
        return Objects.requireNonNull(
                    request(payment).getBody()
                ).getQrCode();
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
