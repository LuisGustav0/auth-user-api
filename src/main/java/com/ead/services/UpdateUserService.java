package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.model.request.UserUpdateRequest;
import com.ead.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserModelAssembler assembler;

    private final UserByIdOrElseThrowService service;

    private final UpdateUserAndPublisherService updateUserAndPublisherService;

    public UserResponse call(final UUID id, final UserUpdateRequest request) {
        final UserModel userModel = this.service.call(id);

        userModel.setFullName(request.getFullName());
        userModel.setPhoneNumber(request.getPhoneNumber());
        userModel.setCpf(request.getCpf());
        userModel.setUpdatedAt(OffsetDateTime.now());

        final UserModel userModelUpdated = this.updateUserAndPublisherService.call(userModel);

        return this.assembler.toResponse(userModelUpdated);
    }
}
