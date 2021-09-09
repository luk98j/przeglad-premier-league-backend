package com.przeglad_premier_league.model.authentication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User getUserByConfirmationID(String confirmationID);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
