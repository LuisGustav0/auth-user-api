package com.ead.repositories;

import com.ead.enums.AuthorityTypeE;
import com.ead.model.AuthorityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<AuthorityModel, UUID> {

    Optional<AuthorityModel> findByName(AuthorityTypeE typeE);
}
