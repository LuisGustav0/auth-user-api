package com.ead.clients;

import com.ead.model.response.PageableCourseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class PageableCourseClientApi {

    private final RestTemplate restTemplate;

    @Value("${ead.api.url.auth-user}")
    private static String REQUEST_URI;

    @Value("${ead.api.url.auth-user.path-courses}")
    private static String PATH_COURSES;

    private String getUrlTemplate() {
        return UriComponentsBuilder.fromHttpUrl(REQUEST_URI)
                                   .path(PATH_COURSES)
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

    public PageableCourseResponse call(final UUID userId, final Pageable pageable) {
        final String url = this.getUrlTemplate();
        final Map<String, Object> params = this.getQueryParams(userId, pageable);

        log.info("GET PageableCourseClientApi.call Request URL: {}", url);

        try {
            final ParameterizedTypeReference<PageableCourseResponse> responseType =
                    new ParameterizedTypeReference<>() {};

            final ResponseEntity<PageableCourseResponse> pageableResult =
                    this.restTemplate.exchange(url, HttpMethod.GET, null, responseType, params);

            return pageableResult.getBody();
        } catch (HttpStatusCodeException ex) {
            log.error("GET PageableCourseClientApi.call URL: {}", url);
        }

        return null;
    }
}
