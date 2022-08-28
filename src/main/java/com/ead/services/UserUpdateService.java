package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.resources.request.UserUpdateRequest;
import com.ead.resources.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserModelAssembler assembler;

    private final UserByIdOrElseThrowService service;

    private final UserRepository repository;

    public UserResponse call(final UUID id, final UserUpdateRequest request) {
        final UserModel userModel = this.service.call(id);

        userModel.setFullName(request.getFullName());
        userModel.setPhoneNumber(request.getPhoneNumber());
        userModel.setCpf(request.getCpf());

        final UserModel userModelUpdated = this.repository.save(userModel);

        return this.assembler.toResponse(userModelUpdated);
    }
}
