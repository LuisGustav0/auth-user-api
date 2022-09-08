package com.ead.model.filter;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    private String fullName;
    private UserTypeE typeE;
    private UserStatusE statusE;

    @Email
    private String email;

    private UUID courseId;
}
