package com.przeglad_premier_league.repository;

import com.przeglad_premier_league.model.key.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeysRepository extends JpaRepository<Key, Integer> {
}
