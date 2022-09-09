package com.ead.assembler;

import com.ead.model.UserModel;
import com.ead.model.response.instructors.InstructorResponse;
import org.springframework.stereotype.Component;

@Component
public class InstructorAssembler {

    public InstructorResponse toResponse(final UserModel userModel) {
        return InstructorResponse.builder()
                                 .id(userModel.getId())
                                 .login(userModel.getLogin())
                                 .email(userModel.getEmail())
                                 .fullName(userModel.getFullName())
                                 .phoneNumber(userModel.getPhoneNumber())
                                 .cpf(userModel.getCpf())
                                 .imageUrl(userModel.getImageUrl())
                                 .statusE(userModel.getStatusE())
                                 .typeE(userModel.getTypeE())
                                 .createdAt(userModel.getCreatedAt())
                                 .updatedAt(userModel.getUpdatedAt())
                                 .build();
    }
}
