package com.ead.config.resttemplate;

import com.ead.config.resttemplate.deserializers.OffsetDateTimeDeserializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    private JavaTimeModule getJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());

        return javaTimeModule;
    }

    private ObjectMapper objectMapper() {
        JavaTimeModule javaTimeModule = getJavaTimeModule();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(javaTimeModule);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper;
    }

    private List<HttpMessageConverter<?>> getListHttpMessageConverter() {
        List<HttpMessageConverter<?>> listHttpMessageConverter = new ArrayList<>();
        ObjectMapper objectMapper = this.objectMapper();

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        listHttpMessageConverter.add(jsonMessageConverter);

        return listHttpMessageConverter;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> listHttpMessageConverter = this.getListHttpMessageConverter();
        restTemplate.setMessageConverters(listHttpMessageConverter);

        return restTemplate;
    }
}
