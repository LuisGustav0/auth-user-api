package com.ead.validations;

import com.ead.exceptions.SubscriptionUserAndCourseExistsException;
import com.ead.repositories.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExistsByUserIdAndCourseIdService {

    private final UserCourseRepository repository;

    public void valid(final UUID userId, final UUID courseId) {
        if (this.repository.existsByUserIdAndCourseId(userId, courseId))
            throw new SubscriptionUserAndCourseExistsException();
    }
}
