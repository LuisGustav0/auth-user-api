package com.ead.repositories;

import com.ead.model.UserCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    boolean existsByUserIdAndCourseId(final UUID userId, final UUID courseId);

    boolean existsByCourseId(final UUID courseId);

    void deleteAllByCourseId(final UUID courseId);

    List<UserCourseModel> findAllByUserId(final UUID userId);
}
