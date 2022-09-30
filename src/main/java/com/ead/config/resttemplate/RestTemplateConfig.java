package com.ead.config.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.ead.config.objectmapper.ObjectMapperConfig.objectMapper;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private static final Duration TIMEOUT = Duration.ofMillis(5000);

    private final RestTemplateBuilder builder;

    private List<HttpMessageConverter<?>> listHttpMessageConverter() {
        final List<HttpMessageConverter<?>> listHttpMessageConverter = new ArrayList<>();

        final var jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper());
        listHttpMessageConverter.add(jsonMessageConverter);

        return listHttpMessageConverter;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return builder.setConnectTimeout(TIMEOUT)
                      .setReadTimeout(TIMEOUT)
                      .messageConverters(listHttpMessageConverter())
                      .build();
    }
}
