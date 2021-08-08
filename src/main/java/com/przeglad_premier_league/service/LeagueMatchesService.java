package com.przeglad_premier_league.service;

import com.przeglad_premier_league.dto.LeagueMatchesDTO;
import com.przeglad_premier_league.repository.LeagueMatchesRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueMatchesService {
    private final LeagueMatchesRepository leagueMatchesRepository;

    public List<LeagueMatchesDTO> getAllMatches(String period){
        return leagueMatchesRepository.findAllBySeasonIdPeriodOrderByMatchIdDesc(period);
    }
}
