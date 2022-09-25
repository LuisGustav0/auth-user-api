package com.ead.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ead.config.objectmapper.ObjectMapperConfig.objectMapper;

@Configuration
@RequiredArgsConstructor
public class RabbitmqConfig {

    private final CachingConnectionFactory cachingConnectionFactory;

    @Value("${ead.broker.exchange.userEvent}")
    private String exchangeUserEvent;

    @Bean
    public FanoutExchange fanoutUserEvent() {
        return new FanoutExchange(exchangeUserEvent);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }
}
