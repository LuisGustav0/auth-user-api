package com.ead.clients.courseapi;

import com.ead.config.security.userdetails.AuthCurrentUserService;
import com.ead.model.response.PageableCourseResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class PageableCourseClientApi {

    private final RestTemplate restTemplate;

    @Value("${ead.api.url.courses}")
    private String requestUri;

    @Value("${ead.api.path.courses}")
    private String pathCourses;

    private final AuthCurrentUserService authCurrentUserService;

    private String getUrlTemplate() {
        return UriComponentsBuilder.fromHttpUrl(requestUri)
                                   .path(pathCourses)
                                   .queryParam("userId", "{userId}")
                                   .queryParam("page", "{page}")
                                   .queryParam("size", "{size}")
                                   .queryParam("sort", "{sort}")
                                   .encode()
                                   .toUriString();
    }

    private Map<String, Object> getQueryParams(final UUID userId,
                                               final Pageable pageable) {
        final int pageNumber = pageable.getPageNumber();
        final int pageSize = pageable.getPageSize();
        final String sort = pageable.getSort().toString().replace(": ", ",");

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("page", pageNumber);
        params.put("size", pageSize);
        params.put("sort", sort);

        return params;
    }

    private HttpEntity<String> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, this.authCurrentUserService.getAuthorizationByHeader());

        return new HttpEntity<>("parameters", headers);
    }

    //@Retry(name = "retryInstance", fallbackMethod = "onFallback")
    @CircuitBreaker(name = "circuitbreakerInstance")
    public PageableCourseResponse call(final UUID userId, final Pageable pageable) {
        final String url = this.getUrlTemplate();

        final Map<String, Object> params = this.getQueryParams(userId, pageable);
        final HttpEntity<String> requestEntity = this.getRequestEntity();

        log.info("GET PageableCourseClientApi.call Request URL: {}", url);

        final ParameterizedTypeReference<PageableCourseResponse> responseType =
                new ParameterizedTypeReference<>() {
                };

        final ResponseEntity<PageableCourseResponse> pageableResult =
                this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, params);

        return pageableResult.getBody();
    }

    public PageableCourseResponse onFallback(final UUID userId, Pageable pageable, Throwable t) {
        log.error("Inside fallbackMethod, cause - {}", t.toString());

        return PageableCourseResponse.builder()
                                     .pageNumber(0)
                                     .pageSize(0)
                                     .totalPages(1)
                                     .totalElements(0)
                                     .data(Collections.emptyList())
                                     .build();
    }
}
