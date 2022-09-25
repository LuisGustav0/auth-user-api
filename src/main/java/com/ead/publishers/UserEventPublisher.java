package com.ead.publishers;

import com.ead.enums.ActionTypeE;
import com.ead.model.request.UserEventRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${ead.broker.exchange.userEvent}")
    private String exchangeUserEvent;

    public void publishUserEvent(@NonNull final UserEventRequest request,
                                 @NonNull final ActionTypeE actionTypeE) {
        request.setActionTypeE(actionTypeE.name());

        this.rabbitTemplate.convertAndSend(exchangeUserEvent, "", request);
    }
}
