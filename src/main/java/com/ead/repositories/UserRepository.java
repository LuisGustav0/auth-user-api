package com.ead.repositories;

import com.ead.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByLogin(final String login);

    boolean existsByEmail(final String email);
}
