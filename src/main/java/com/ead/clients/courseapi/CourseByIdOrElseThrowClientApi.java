package com.ead.clients.courseapi;

import com.ead.exceptions.ServiceCourseUnavailableException;
import com.ead.exceptions.UnexpectedErrorException;
import com.ead.model.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CourseByIdOrElseThrowClientApi {

    private final RestTemplate restTemplate;

    @Value("${ead.api.url.auth-user}")
    private String REQUEST_URI;

    @Value("${ead.api.path.courses}")
    private String PATH_COURSES;

    private String getUrlTemplate(final UUID courseId) {
        return UriComponentsBuilder.fromHttpUrl(REQUEST_URI)
                                   .path(PATH_COURSES + "/")
                                   .path("/" + courseId)
                                   .encode()
                                   .toUriString();
    }

    public CourseResponse call(final UUID userId) {
        try {
            final String url = this.getUrlTemplate(userId);

            log.error("CourseByIdOrElseThrowClientApi.call URL: {}", url);

            final ResponseEntity<CourseResponse> response =
                    this.restTemplate.exchange(url, HttpMethod.GET, null, CourseResponse.class);

            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            log.error("CourseByIdOrElseThrowClientApi.call Error", ex);

            if (!ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new UnexpectedErrorException(ex);
        } catch (Exception ex) {
            log.error("CourseByIdOrElseThrowClientApi.call Error", ex);

            throw new ServiceCourseUnavailableException(ex);
        }

        return null;
    }
}
