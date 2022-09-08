package com.ead.validations;

import com.ead.exceptions.LoginIsAlreadyBeingUsedException;
import com.ead.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ExistsByUsernameService {

    private final UserRepository repository;

    public void valid(final String username) {
        log.warn("ExistsByLoginService.valid Username: {}", username);

        if (this.repository.existsByLogin(username))
            throw new LoginIsAlreadyBeingUsedException(username);
    }
}
