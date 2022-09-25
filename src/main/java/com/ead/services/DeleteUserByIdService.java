package com.ead.services;

import com.ead.model.UserModel;
import com.ead.model.response.DeleteUserResponse;
import com.ead.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeleteUserByIdService {

    private final UserRepository repository;

    private final UserByIdOrElseThrowService service;

    @Transactional
    public DeleteUserResponse call(final UUID id) {
        final UserModel userModel = this.service.call(id);

        this.repository.delete(userModel);

        return DeleteUserResponse.builder()
                                 .message("Usuário deletado com sucesso!")
                                 .build();
    }
}
