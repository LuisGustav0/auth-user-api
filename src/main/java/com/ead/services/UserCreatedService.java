package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.assembler.UserRegisterRequestAssembler;
import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.resources.request.UserCreatedRequest;
import com.ead.resources.response.UserResponse;
import com.ead.validations.ExistsByEmailService;
import com.ead.validations.ExistsByLoginService;
import com.ead.validations.PasswordAndConfirmDifferentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedService {

    private final UserModelAssembler userModelAssembler;
    private final UserRegisterRequestAssembler requestAssembler;

    private final ExistsByLoginService existsByLoginService;
    private final ExistsByEmailService existsByEmailService;
    private final PasswordAndConfirmDifferentService passwordValidService;

    private final UserRepository repository;

    public UserResponse call(final UserCreatedRequest request) {
        this.existsByLoginService.valid(request.getLogin());
        this.existsByEmailService.valid(request.getEmail());
        this.passwordValidService.valid(request.getPassword(), request.getConfirmPassword());

        final UserModel userModel = this.requestAssembler.toUserActiveAndStudent(request);

        final UserModel userModelSave = this.repository.save(userModel);

        return this.userModelAssembler.toResponse(userModelSave);
    }
}
