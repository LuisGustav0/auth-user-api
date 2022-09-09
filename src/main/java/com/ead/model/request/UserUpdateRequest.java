package com.ead.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserUpdateRequest {

    @NotEmpty
    @Size(min = 4, max = 150)
    private String fullName;

    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;

    @NotEmpty
    @Size(max = 20)
    private String cpf;
}
