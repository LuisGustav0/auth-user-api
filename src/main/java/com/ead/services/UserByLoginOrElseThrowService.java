package com.ead.services;

import com.ead.exceptions.UserNotFoundException;
import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserByLoginOrElseThrowService {

    private final UserRepository repository;

    public UserModel call(final String login) {
        return this.repository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }
}
