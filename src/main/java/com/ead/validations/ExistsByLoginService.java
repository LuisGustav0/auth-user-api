package com.ead.validations;

import com.ead.exceptions.LoginIsAlreadyBeingUsedException;
import com.ead.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistsByLoginService {

    private final UserRepository repository;

    public void valid(final String login) {
        if (this.repository.existsByLogin(login))
            throw new LoginIsAlreadyBeingUsedException();
    }
}
