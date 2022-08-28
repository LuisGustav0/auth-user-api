package com.ead.services;

import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.resources.request.UserUpdatePasswordRequest;
import com.ead.resources.response.UserUpdatePasswordResponse;
import com.ead.validations.PasswordAndConfirmDifferentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUpdatePasswordService {

    private final UserRepository repository;
    private final UserByIdOrElseThrowService service;
    private final PasswordAndConfirmDifferentService validatedService;

    public UserUpdatePasswordResponse call(final UUID id, final UserUpdatePasswordRequest request) {
        this.validatedService.valid(request.getPassword(), request.getConfirmPassword());

        final UserModel userModel = this.service.call(id);

        userModel.setPassword(request.getPassword());

        this.repository.save(userModel);

        return UserUpdatePasswordResponse.builder()
                                         .message("Senha alterado com sucesso.")
                                         .build();
    }
}
