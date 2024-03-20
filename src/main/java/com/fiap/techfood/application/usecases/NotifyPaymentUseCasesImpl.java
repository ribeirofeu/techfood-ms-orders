package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.interfaces.gateways.OrderRepository;
import com.fiap.techfood.application.interfaces.usecases.NotifyPaymentUserCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.payment.PaymentStatus;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class NotifyPaymentUseCasesImpl implements NotifyPaymentUserCases {

    private OrderRepository orderRepository;
    @Override
    public void execute(Long orderId, PaymentStatus paymentStatus) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Pedido não encontrado"));

        if (order.getCustomer() == null) {
            return;
        }

        System.out.println("Notificando o cliente " + order.getCustomer().getName()  + " que o status do pagamento é " + paymentStatus.name());
    }
}
