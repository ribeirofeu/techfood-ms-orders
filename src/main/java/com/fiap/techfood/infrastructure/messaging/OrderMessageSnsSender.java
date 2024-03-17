package com.fiap.techfood.infrastructure.messaging;

import com.fiap.techfood.application.interfaces.gateways.OrderMessageSender;

import com.fiap.techfood.domain.commons.Message;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OrderMessageSnsSender implements OrderMessageSender {
    @Value("${messaging.output}")
    private String topic;

    private final SnsTemplate snsTemplate;

    public OrderMessageSnsSender(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    @Override
    public <T extends Message> void publish(T message) {
        snsTemplate.convertAndSend(topic, message);
        log.info("Message sent to the topic");
    }
}
