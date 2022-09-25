package com.ead.services;

import com.ead.model.UserModel;
import com.ead.model.response.DeleteUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeleteUserByIdService {

    private final UserByIdOrElseThrowService service;

    private final DeleteUserAndPublisherService deleteUserAndPublisherService;

    @Transactional
    public DeleteUserResponse call(final UUID id) {
        final UserModel userModel = this.service.call(id);

        this.deleteUserAndPublisherService.call(userModel);

        return DeleteUserResponse.builder()
                                 .message("Usuário deletado com sucesso!")
                                 .build();
    }
}
