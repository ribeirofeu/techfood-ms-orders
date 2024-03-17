package com.fiap.techfood.application.interfaces.gateways;

import com.fiap.techfood.domain.commons.Event;

public interface OrderMessageSender {
    <T extends Event> void publish(T event);
}
