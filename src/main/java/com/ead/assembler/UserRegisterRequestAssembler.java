package com.ead.assembler;

import com.ead.enums.AuthorityTypeE;
import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import com.ead.model.AuthorityModel;
import com.ead.model.UserModel;
import com.ead.model.request.UserCreatedRequest;
import com.ead.services.authorities.AuthorityByNameOrElseThrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserRegisterRequestAssembler {

    private final AuthorityByNameOrElseThrowService authorityByNameOrElseThrowService;

    private final PasswordEncoder passwordEncoder;

    public UserModel toUserActiveAndStudent(final UserCreatedRequest request) {
        final AuthorityModel authority = this.authorityByNameOrElseThrowService.call(AuthorityTypeE.ROLE_STUDENT);
        final String passwordEncrypted = passwordEncoder.encode(request.getPassword());

        return UserModel.builder()
                        .login(request.getLogin())
                        .email(request.getEmail())
                        .password(passwordEncrypted)
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .cpf(request.getCpf())
                        .imageUrl(request.getImageUrl())
                        .statusE(UserStatusE.ACTIVE)
                        .typeE(UserTypeE.STUDENT)
                        .createdAt(OffsetDateTime.now())
                        .updatedAt(OffsetDateTime.now())
                        .listAuthority(Collections.singleton(authority))
                        .build();
    }
}
