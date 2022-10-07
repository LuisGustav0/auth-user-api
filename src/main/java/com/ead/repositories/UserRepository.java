package com.ead.repositories;

import com.ead.model.UserModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID>,
        JpaSpecificationExecutor<UserModel> {

    boolean existsByLogin(final String login);

    boolean existsByEmail(final String email);

    @EntityGraph(attributePaths = "listAuthority", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT u FROM UserModel u  where upper(u.login) = upper(:login)")
    Optional<UserModel> findByLogin(final String login);
}
