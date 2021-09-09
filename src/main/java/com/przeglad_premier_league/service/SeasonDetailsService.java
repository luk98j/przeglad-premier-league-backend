package com.przeglad_premier_league.service;

import com.przeglad_premier_league.dto.SeasonDetailsDTO;
import com.przeglad_premier_league.repository.SeasonDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonDetailsService {
    private final SeasonDetailsRepository seasonDetailsRepository;

    public List<SeasonDetailsDTO> getAllClubsBySeason(String seasonPeriod){
        return seasonDetailsRepository.findAllBySeasonPeriodOrderByPosition(seasonPeriod);
    }
}
