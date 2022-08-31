package com.ead.model.filter;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    private UserTypeE typeE;
    private UserStatusE statusE;
    private String email;

    public String getEmail() {
        return email == null ? "1=1" : email;
    }
}
