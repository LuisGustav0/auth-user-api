package com.ead.model.response;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
