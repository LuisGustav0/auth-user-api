package com.ead.resources.response;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends RepresentationModel<UserResponse> {

    private UUID id;
    private String login;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String cpf;
    private UserStatusE statusE;
    private UserTypeE typeE;
    private String imageUrl;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
