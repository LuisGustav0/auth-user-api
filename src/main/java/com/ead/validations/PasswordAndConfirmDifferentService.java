package com.ead.validations;

import com.ead.exceptions.ConfirmPasswordRequiredException;
import com.ead.exceptions.PasswordDifferentConfirmPasswordException;
import com.ead.exceptions.PasswordRequiredException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordAndConfirmDifferentService {

    public void valid(final String password, final String confirmPassword) {
        if (StringUtils.isEmpty(password))
            throw new PasswordRequiredException();

        if (StringUtils.isEmpty(confirmPassword))
            throw new ConfirmPasswordRequiredException();

        if (!password.equals(confirmPassword))
            throw new PasswordDifferentConfirmPasswordException();
    }
}
