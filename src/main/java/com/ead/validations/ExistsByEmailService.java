package com.ead.validations;

import com.ead.exceptions.EmailIsAlreadyBeingUsedException;
import com.ead.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistsByEmailService {

    private final UserRepository repository;

    public void valid(final String email) {
        if (this.repository.existsByEmail(email))
            throw new EmailIsAlreadyBeingUsedException();
    }
}
