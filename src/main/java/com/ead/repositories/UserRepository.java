package com.ead.repositories;

import com.ead.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID>,
        JpaSpecificationExecutor<UserModel> {

    boolean existsByLogin(final String login);

    boolean existsByEmail(final String email);
}
