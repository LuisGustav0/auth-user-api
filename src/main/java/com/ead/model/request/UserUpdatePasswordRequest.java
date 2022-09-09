package com.ead.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserUpdatePasswordRequest {

    @NotEmpty
    @Size(max = 255)
    private String password;

    @NotEmpty
    @Size(max = 255)
    private String confirmPassword;
}
