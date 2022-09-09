package com.ead.services;

import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.model.response.DeleteUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeleteByIdService {

    private final UserRepository repository;

    private final UserByIdOrElseThrowService service;

    public DeleteUserResponse call(final UUID id) {
        final UserModel userModel = this.service.call(id);

        this.repository.delete(userModel);

        return DeleteUserResponse.builder()
                                 .message("Usuário deletado com sucesso!")
                                 .build();
    }
}
