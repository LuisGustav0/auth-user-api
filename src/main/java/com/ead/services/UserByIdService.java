package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.model.response.UserResponse;
import com.ead.services.auth.CurrentUserIdDifferentUserIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserByIdService {

    private final UserModelAssembler userModelAssembler;
    private final UserByIdOrElseThrowService service;

    private final CurrentUserIdDifferentUserIdService currentUserIdDifferentUserIdService;

    public UserResponse call(final UUID id) {
        this.currentUserIdDifferentUserIdService.call(id);

        final UserModel userModel = this.service.call(id);

        return this.userModelAssembler.toResponse(userModel);
    }
}
