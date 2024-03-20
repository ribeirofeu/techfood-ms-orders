package com.fiap.techfood.infrastructure.messaging.listeners;

import com.fiap.techfood.application.interfaces.usecases.NotifyPaymentUserCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.infrastructure.messaging.events.ReceivedPaymentStatusEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@Profile("!test")
public class NotifyPaymentListener {
    private NotifyPaymentUserCases notifyPaymentUserCases;

    @SqsListener(value = "${events.queues.notify-payment}")
    public void listenReceivedPaymentEvent(ReceivedPaymentStatusEvent event) {
        log.info("Evento de notificação pagamento recebido. Order Id: {}", event.getOrderId());
        try {
            notifyPaymentUserCases.execute(event.getOrderId(), event.getPaymentStatus());
            log.info("Evento processado com sucesso. Order id: {}", event.getOrderId());
        } catch (BusinessException businessException) {
            log.info("A mensagem não pode ser processada. Motivo: {}", businessException.getMessage());
        } catch (Exception exception) {
            log.error("Erro ao processar a mensagem. Motivo: {}", exception.getMessage());
            throw exception;
        }
    }

}
