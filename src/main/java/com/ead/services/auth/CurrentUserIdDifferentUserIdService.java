package com.ead.services.auth;

import com.ead.config.security.userdetails.AuthCurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrentUserIdDifferentUserIdService {

    private final AuthCurrentUserService authCurrentUserService;

    public void call(final UUID id) {
        final UUID currentUserId = this.authCurrentUserService.getCurrentUserId();

        if (!currentUserId.equals(id))
            throw new AccessDeniedException("Forbidden");
    }
}
