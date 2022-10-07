package com.ead.model.request.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    private String login;
    private String password;
}
