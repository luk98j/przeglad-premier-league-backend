package com.przeglad_premier_league.repository;

import com.przeglad_premier_league.model.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByClubName(String clubName);

    List<Club> findByClubNameContains(String clubName);
}
