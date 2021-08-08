package com.przeglad_premier_league.controller;

import com.przeglad_premier_league.dto.LeagueMatchesDTO;
import com.przeglad_premier_league.dto.SeasonDetailsDTO;
import com.przeglad_premier_league.model.season.SeasonPeriod;
import com.przeglad_premier_league.service.LeagueMatchesService;
import com.przeglad_premier_league.service.SeasonDetailsService;
import com.przeglad_premier_league.service.SeasonPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/public")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonPeriodService seasonPeriodService;
    private final SeasonDetailsService seasonDetailsService;
    private final LeagueMatchesService leagueMatchesService;

    @GetMapping("/season-list")
    public ResponseEntity<List<SeasonPeriod>> getAllSeasonPeriods(){
        return ResponseEntity.ok(seasonPeriodService.getAllSeasonsPeriod());
    }

    @GetMapping("/season-table")
    public ResponseEntity<List<SeasonDetailsDTO>> getDetailsAboutSeason(@RequestParam String period){
        return ResponseEntity.ok(seasonDetailsService.getAllClubsBySeason(period));
    }

    @GetMapping("/season-matches")
    public ResponseEntity<List<LeagueMatchesDTO>> getLeagueMatches(@RequestParam String period){
        return ResponseEntity.ok(leagueMatchesService.getAllMatches(period));
    }
}
