package com.fiap.techfood.infrastructure.messaging.listeners;

import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.infrastructure.messaging.events.ReceivedPaymentStatusEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ReceivedPaymentListener {
    private OrderUseCases orderUseCases;

    @SqsListener(value = "${events.queues.payment-received}")
    public void listenReceivedPaymentEvent(ReceivedPaymentStatusEvent event) {
        log.info("Evento de recebimento de pagamento recebido. Order Id: {}", event.getOrderId());
        try {
            orderUseCases.updateOrderStatus(event.getOrderId(), OrderStatus.getByPaymentStatus(event.getPaymentStatus()));
            log.info("Evento processado com sucesso. Order id: {}", event.getOrderId());
        } catch (BusinessException businessException) {
            log.info("A mensagem não pode ser processada. Motivo: {}", businessException.getMessage());
        } catch (Exception exception) {
            log.error("Erro ao processar a mensagem. Motivo: {}", exception.getMessage());
            throw exception;
        }
    }

}
