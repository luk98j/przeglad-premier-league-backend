package com.przeglad_premier_league.model.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
