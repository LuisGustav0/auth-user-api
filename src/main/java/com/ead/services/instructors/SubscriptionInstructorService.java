package com.ead.services.instructors;

import com.ead.assembler.InstructorAssembler;
import com.ead.assembler.UserModelAssembler;
import com.ead.enums.AuthorityTypeE;
import com.ead.enums.UserTypeE;
import com.ead.model.AuthorityModel;
import com.ead.model.UserModel;
import com.ead.model.request.instructors.InstructorRequest;
import com.ead.model.response.instructors.InstructorResponse;
import com.ead.repositories.UserRepository;
import com.ead.services.UpdateUserAndPublisherService;
import com.ead.services.UserByIdOrElseThrowService;
import com.ead.services.authorities.AuthorityByNameOrElseThrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubscriptionInstructorService {

    private final InstructorAssembler assembler;
    private final UserByIdOrElseThrowService userByIdOrElseThrowService;

    private final UpdateUserAndPublisherService updateUserAndPublisherService;

    private final AuthorityByNameOrElseThrowService authorityByNameOrElseThrowService;

    public InstructorResponse call(final InstructorRequest request) {
        log.debug("SubscriptionInstructorService.call Request: {}", request);

        final AuthorityModel authority = this.authorityByNameOrElseThrowService.call(AuthorityTypeE.ROLE_INSTRUCTOR);

        final UserModel user = this.userByIdOrElseThrowService.call(request.getUserId());

        log.debug("SubscriptionInstructorService.call User: {}", user);

        user.setTypeE(UserTypeE.INSTRUCTOR);
        user.setUpdatedAt(OffsetDateTime.now());
        user.getListAuthority().add(authority);

        final UserModel userSaved = this.updateUserAndPublisherService.call(user);

        log.debug("SubscriptionInstructorService.call User saved: {}", userSaved);

        return this.assembler.toResponse(userSaved);
    }
}
