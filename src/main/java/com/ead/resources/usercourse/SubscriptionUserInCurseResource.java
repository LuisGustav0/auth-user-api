package com.ead.resources.usercourse;

import com.ead.model.request.usercourse.SubscriptionUserInCurseRequest;
import com.ead.model.response.usercourse.SubscriptionUserInCurseResponse;
import com.ead.services.usercourse.SubscriptionUserInCurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SubscriptionUserInCurseResource {

    private final SubscriptionUserInCurseService service;

    @PostMapping("/users/{userId}/courses/subscription")
    public ResponseEntity<SubscriptionUserInCurseResponse> call(@PathVariable UUID userId,
                                                                @RequestBody @Valid SubscriptionUserInCurseRequest request) {
        final SubscriptionUserInCurseResponse response = service.call(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
