package com.ead.model.response.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {

    private final String token;
    private String type = "Bearer";
}
