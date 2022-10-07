package com.ead.services.authorities;

import com.ead.enums.AuthorityTypeE;
import com.ead.exceptions.AuthorityNotFoundException;
import com.ead.model.AuthorityModel;
import com.ead.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityByNameOrElseThrowService {

    private final AuthorityRepository repository;

    public AuthorityModel call(final AuthorityTypeE typeE) {
        return this.repository.findByName(typeE).orElseThrow(AuthorityNotFoundException::new);
    }
}
