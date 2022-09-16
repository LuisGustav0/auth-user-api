package com.ead.services.usercourse;

import com.ead.model.response.usercourse.DeleteUserCourseResponse;
import com.ead.repositories.UserCourseRepository;
import com.ead.validations.usercourse.NotExistsUserCourseByCourseIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserCourseByCourseIdService {

    private final UserCourseRepository repository;

    private final NotExistsUserCourseByCourseIdService notExistsUserCourseByCourseIdService;

    @Transactional
    public DeleteUserCourseResponse call(final UUID courseId) {
        this.notExistsUserCourseByCourseIdService.valid(courseId);

        this.repository.deleteAllByCourseId(courseId);

        return DeleteUserCourseResponse.builder()
                                       .message("Curso do usuario deletado com sucesso!")
                                       .build();
    }
}
