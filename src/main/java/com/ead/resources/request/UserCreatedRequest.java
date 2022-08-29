package com.ead.resources.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserCreatedRequest {

    @NotBlank
    @Size(min = 4, max = 50)
    private String login;

    @Email
    @Size(max = 50)
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    @Size(min = 4, max = 150)
    private String fullName;

    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;

    @NotEmpty
    @Size(max = 20)
    private String cpf;

    @NotEmpty
    @Size(max = 200)
    private String imageUrl;
}
