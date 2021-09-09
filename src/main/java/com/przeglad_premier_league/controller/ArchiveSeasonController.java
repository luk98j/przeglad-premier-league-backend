package com.przeglad_premier_league.controller;

import com.przeglad_premier_league.dto.SeasonDetailsDTO;
import com.przeglad_premier_league.model.season.SeasonPeriod;
import com.przeglad_premier_league.service.SeasonDetailsService;
import com.przeglad_premier_league.service.SeasonPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/private")
public class ArchiveSeasonController {

    private final SeasonPeriodService seasonPeriodService;
    private final SeasonDetailsService seasonDetailsService;

    @GetMapping("/archive/season-list")
    public ResponseEntity<List<SeasonPeriod>> getAllArchiveSeasonPeriods(){
        return ResponseEntity.ok(seasonPeriodService.getArchiveSeasonsPeriod());
    }

    @GetMapping("/archive/season-table")
    public ResponseEntity<List<SeasonDetailsDTO>> getDetailsAboutSeason(@RequestParam String period){
        return ResponseEntity.ok(seasonDetailsService.getAllClubsBySeason(period));
    }
}
