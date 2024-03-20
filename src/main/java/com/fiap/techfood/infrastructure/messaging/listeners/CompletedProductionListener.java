package com.fiap.techfood.infrastructure.messaging.listeners;

import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.infrastructure.messaging.events.CompletedProductionEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@Profile("!test")
public class CompletedProductionListener {
    private OrderUseCases orderUseCases;

    @SqsListener(value = "${events.queues.completed-production}")
    public void listenCompletedProductionEvent(CompletedProductionEvent event) {
        log.info("Evento COMPLETED_PRODUCTION recebido. Order Id: {}", event.getOrderId());
        try {
            orderUseCases.updateOrderStatus(event.getOrderId(), OrderStatus.COMPLETED);
            log.info("Evento processado com sucesso. Order id: {}", event.getOrderId());
        } catch (BusinessException businessException) {
            log.info("A mensagem não pode ser processada. Motivo: {}", businessException.getMessage());
        } catch (Exception exception) {
            log.error("Erro ao processar a mensagem. Motivo: {}", exception.getMessage());
            throw exception;
        }
    }

}
