package com.przeglad_premier_league.repository;

import com.przeglad_premier_league.dto.LeagueMatchesDTO;
import com.przeglad_premier_league.model.season.LeagueMatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueMatchesRepository extends JpaRepository<LeagueMatches, Long> {
    List<LeagueMatches> findByMatchId(String matchId);
    List<LeagueMatchesDTO> findAllBySeasonIdPeriodOrderByMatchIdDesc(String seasonIdPeriod);
}
