package com.ead.clients.courseapi;

import com.ead.exceptions.ServiceCourseUnavailableException;
import com.ead.exceptions.UnexpectedErrorException;
import com.ead.model.response.courseuser.DeleteCourseUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class DeleteCourseUserByUserIdClientApi {

    private final RestTemplate restTemplate;

    @Value("${ead.api.url.courses}")
    private String REQUEST_URI;

    @Value("${ead.api.path.courses}")
    private String PATH_COURSES;

    @Value("${ead.api.path.users}")
    private String PATH_USERS;

    private String getUrlTemplate(final UUID userId) {
        return UriComponentsBuilder.fromHttpUrl(REQUEST_URI)
                                   .path(PATH_COURSES)
                                   .path(PATH_USERS)
                                   .path("/" + userId)
                                   .encode()
                                   .toUriString();
    }

    public DeleteCourseUserResponse call(final UUID userId) {
        try {
            final String url = this.getUrlTemplate(userId);

            final ResponseEntity<DeleteCourseUserResponse> responseEntity =
                    this.restTemplate.exchange(url, HttpMethod.DELETE, null, DeleteCourseUserResponse.class);

            return responseEntity.getBody();
        } catch (HttpStatusCodeException ex) {
            throw new UnexpectedErrorException(ex);
        } catch (Exception ex) {
            throw new ServiceCourseUnavailableException(ex);
        }
    }
}
