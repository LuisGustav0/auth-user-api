package com.ead.services;

import com.ead.clients.courseapi.DeleteCourseUserByUserIdClientApi;
import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.model.response.DeleteUserResponse;
import com.ead.services.usercourse.DeleteAllUserCourseByUserIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeleteUserByIdService {

    private final UserRepository repository;

    private final UserByIdOrElseThrowService service;

    private final DeleteAllUserCourseByUserIdService deleteAllUserCourseByUserIdService;

    private final DeleteCourseUserByUserIdClientApi deleteCourseUserByUserIdClientApi;

    @Transactional
    public DeleteUserResponse call(final UUID id) {
        final UserModel userModel = this.service.call(id);

        boolean deleteUserCourseInCourseApi = this.deleteAllUserCourseByUserIdService.call(id);

        this.repository.delete(userModel);

        if (deleteUserCourseInCourseApi)
            this.deleteCourseUserByUserIdClientApi.call(id);

        return DeleteUserResponse.builder()
                                 .message("Usuário deletado com sucesso!")
                                 .build();
    }
}
