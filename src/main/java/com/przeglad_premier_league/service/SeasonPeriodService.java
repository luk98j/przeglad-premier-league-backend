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
        if(!period.contains("/"))
        {
            period = addChar(period, '/', 4);
        }
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

    public List<SeasonPeriod> getAllSeasonsPeriod(){
        return seasonPeriodRepository.findAllByOrderByStartDateDesc();
    }

    public List<SeasonPeriod> getArchiveSeasonsPeriod(){
        List<SeasonPeriod> seasonPeriods = seasonPeriodRepository.findAllByOrderByStartDateDesc();
        seasonPeriods.remove(0);
        return seasonPeriods;
    }
    private LocalDate createDate(String month, String year){
        return LocalDate.parse(year.concat(month));
    }

    public String addChar(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }
}
