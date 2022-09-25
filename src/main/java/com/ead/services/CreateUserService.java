package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.assembler.UserRegisterRequestAssembler;
import com.ead.model.UserModel;
import com.ead.model.request.UserCreatedRequest;
import com.ead.model.response.UserResponse;
import com.ead.validations.ExistsByEmailService;
import com.ead.validations.ExistsByUsernameService;
import com.ead.validations.PasswordAndConfirmDifferentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserModelAssembler userModelAssembler;
    private final UserRegisterRequestAssembler requestAssembler;

    private final ExistsByUsernameService existsByUsernameService;
    private final ExistsByEmailService existsByEmailService;
    private final PasswordAndConfirmDifferentService passwordValidService;

   private final SaveUserAndPublisherService saveUserAndPublisherService;

    public UserResponse call(final UserCreatedRequest request) {
        log.debug("UserCreatedService.call Request: {}", request);

        this.existsByUsernameService.valid(request.getLogin());
        this.existsByEmailService.valid(request.getEmail());
        this.passwordValidService.valid(request.getPassword(), request.getConfirmPassword());

        final UserModel userModel = this.requestAssembler.toUserActiveAndStudent(request);

        final UserModel userModelSaved = this.saveUserAndPublisherService.call(userModel);

        log.debug("UserCreatedService.call User saved: {}", userModelSaved);

        return this.userModelAssembler.toResponse(userModelSaved);
    }
}
