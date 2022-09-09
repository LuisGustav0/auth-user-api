package com.ead.assembler;

import com.ead.model.UserModel;
import com.ead.resources.users.UserByIdResource;
import com.ead.resources.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler {

    public UserResponse toResponse(final UserModel userModel) {
        final UserResponse response = UserResponse.builder()
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

        response.add(linkTo(methodOn(UserByIdResource.class).getById(userModel.getId())).withSelfRel());

        return response;
    }

    public List<UserResponse> toListResponse(final List<UserModel> listUserModel) {
        return listUserModel.stream().map(this::toResponse).toList();
    }
}
