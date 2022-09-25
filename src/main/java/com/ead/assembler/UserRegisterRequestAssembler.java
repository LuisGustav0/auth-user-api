package com.ead.assembler;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import com.ead.model.UserModel;
import com.ead.model.request.UserCreatedRequest;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class UserRegisterRequestAssembler {

    public UserModel toUserActiveAndStudent(final UserCreatedRequest request) {
        return UserModel.builder()
                        .login(request.getLogin())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .cpf(request.getCpf())
                        .imageUrl(request.getImageUrl())
                        .statusE(UserStatusE.ACTIVE)
                        .typeE(UserTypeE.STUDENT)
                        .createdAt(OffsetDateTime.now())
                        .updatedAt(OffsetDateTime.now())
                        .build();
    }
}
