package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.model.request.UserUpdateImageRequest;
import com.ead.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUpdateImageService {
    private final UserModelAssembler assembler;
    private final UserByIdOrElseThrowService service;

    private final UserRepository repository;

    public UserResponse call(final UUID id, final UserUpdateImageRequest request) {
        final UserModel userModel = this.service.call(id);

        userModel.setImageUrl(request.getImageUrl());

        final UserModel userModelUpdated = this.repository.save(userModel);

        return this.assembler.toResponse(userModelUpdated);
    }
}
