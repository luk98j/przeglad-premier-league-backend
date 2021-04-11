package com.przeglad_premier_league.service;

import com.przeglad_premier_league.model.season.SeasonPeriod;
import com.przeglad_premier_league.repository.SeasonPeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonPeriodService {

    private final SeasonPeriodRepository seasonPeriodRepository;
    private static final String START_DATE = "-09-12";
    private static final String END_DATE = "-05-23";

    public SeasonPeriod getSeasonPeriod(String period){
        String[] years= period.split("/");
        List<SeasonPeriod> seasonPeriods = seasonPeriodRepository.findByPeriod(period);
        if(seasonPeriods.isEmpty()){
            SeasonPeriod seasonPeriod = SeasonPeriod.builder()
                    .period(period)
                    .startDate(createDate(START_DATE,years[0]))
                    .endDate(createDate(END_DATE,years[1]))
                    .build();
            seasonPeriodRepository.save(seasonPeriod);
            return seasonPeriod;
        } else {
            return seasonPeriods.get(0);
        }
    }

    private LocalDate createDate(String month, String year){
        return LocalDate.parse(year.concat(month));
    }
}
