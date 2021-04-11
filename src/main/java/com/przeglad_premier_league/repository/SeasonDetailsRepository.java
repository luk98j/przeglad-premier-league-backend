package com.przeglad_premier_league.repository;

import com.przeglad_premier_league.model.season.SeasonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonDetailsRepository extends JpaRepository<SeasonDetails, Long> {
    List<SeasonDetails> findBySeasonIdAndClubId(Long seasonId, Long clubId);
}
