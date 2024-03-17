package com.fiap.techfood.application.interfaces.gateways;

import com.fiap.techfood.domain.commons.Message;

public interface OrderMessageSender {
    <T extends Message> void publish(T message);
}
