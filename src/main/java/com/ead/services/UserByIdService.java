package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.resources.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserByIdService {

    private final UserModelAssembler userModelAssembler;

    private final UserByIdOrElseThrowService service;

    public UserResponse call(final UUID id) {
        final UserModel userModel = this.service.call(id);

        return this.userModelAssembler.toResponse(userModel);
    }
}
