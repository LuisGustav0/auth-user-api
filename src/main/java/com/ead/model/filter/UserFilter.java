package com.ead.model.filter;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    private String fullName;
    private UserTypeE typeE;
    private UserStatusE statusE;

    @Email
    private String email;

    public boolean isFullNameNotEmpty() {
        return StringUtils.isNotEmpty(this.fullName);
    }

    public boolean isTypeNotNull() {
        return this.typeE != null;
    }

    public boolean isStatusNotNull() {
        return this.statusE != null;
    }

    public boolean isEmailNotEmpty() {
        return StringUtils.isNotEmpty(this.email);
    }

    public boolean isAllFilterEmpty() {
        return this.isTypeNotNull() &&
                this.isStatusNotNull() &&
                this.isEmailNotEmpty() &&
                this.isFullNameNotEmpty();
    }

    public boolean isFilterNotEmpty() {
        return !this.isAllFilterEmpty();
    }
}
