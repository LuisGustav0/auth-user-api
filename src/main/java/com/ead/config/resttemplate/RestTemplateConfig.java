package com.ead.config.resttemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.ead.config.objectmapper.ObjectMapperConfig.objectMapper;

@Configuration
public class RestTemplateConfig {

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
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(listHttpMessageConverter());

        return restTemplate;
    }
}
