package com.ead.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserEventRequest {

    private UUID id;
    private String login;
    private String email;
    private String fullName;
    private String statusE;
    private String typeE;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;
    private String actionTypeE;

    private String createdAt;
    private String updatedAt;
}
