package com.przeglad_premier_league.controller;

import com.przeglad_premier_league.model.season.SeasonPeriod;
import com.przeglad_premier_league.service.SeasonPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/public")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonPeriodService seasonPeriodService;

    @GetMapping("/season-list")
    public ResponseEntity<List<SeasonPeriod>> getAllSeasonPeriods(){
        return ResponseEntity.ok(seasonPeriodService.getAllSeasonsPeriod());
    }
}
