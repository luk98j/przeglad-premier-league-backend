package com.przeglad_premier_league.service.scheduled;

import com.przeglad_premier_league.model.club.Club;
import com.przeglad_premier_league.model.key.Key;
import com.przeglad_premier_league.model.season.SeasonDetails;
import com.przeglad_premier_league.model.season.SeasonPeriod;
import com.przeglad_premier_league.repository.KeysRepository;
import com.przeglad_premier_league.repository.SeasonDetailsRepository;
import com.przeglad_premier_league.service.ClubService;
import com.przeglad_premier_league.service.FootballAPIService;
import com.przeglad_premier_league.service.SeasonPeriodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableAsync
public class OldSeasonScheduled {

    private final KeysRepository keysRepository;
    private final FootballAPIService footballAPIService;
    private final SeasonPeriodService seasonPeriodService;
    private final ClubService clubService;
    private final SeasonDetailsRepository seasonDetailsRepository;
    @Value("${ppl.fdapi.key}")
    private String apiKey;
    @Value("${ppl.season.id}")
    private String seasonId;
    private static final String COUNTRY = "ENG";

    @Async
    @Scheduled(cron = "0 */45 * * * ?")
    public void downloadAllOldData(){
        List<Key> keyList = keysRepository.findAll();
        for (Key key:keyList) {
            if(!key.isDownload() && key.getKeyId()!= Integer.valueOf(seasonId)){
                JSONArray jsonArray = footballAPIService.getSeasonDetails(apiKey, String.valueOf(key.getKeyId()));
                JSONObject object;
                SeasonPeriod seasonPeriod = seasonPeriodService.getSeasonPeriod(key.getYear());
                log.info("I start parsing the old season " + key.getYear());
                for (int i=0; i<jsonArray.length();i++) {
                    object = jsonArray.getJSONObject(i);
                    Club club = clubService.getClubOrCreateNew(object.get("name").toString());
                    log.info("Data parsing for {}", club.getClubName());
                    List<SeasonDetails> seasonDetailsList = seasonDetailsRepository.findBySeasonIdAndClubId(seasonPeriod.getId(), club.getId());
                    if (seasonDetailsList.isEmpty()) {
                        seasonDetailsRepository.save(seasonDetailsBuilder(club, seasonPeriod, object));
                    } else {
                        SeasonDetails seasonDetails = updateSeasonDetails(seasonDetailsList.get(0), object);
                        seasonDetailsRepository.save(seasonDetails);
                    }
                }
                key.setDownload(true);
                keysRepository.save(key);
            }
        }
    }

    private SeasonDetails seasonDetailsBuilder(Club club, SeasonPeriod seasonPeriod, JSONObject object){
        return SeasonDetails.builder()
                .season(seasonPeriod)
                .club(club)
                .position(object.getInt("position"))
                .points(object.getInt("points"))
                .seasonGoals(object.getInt("seasonGoals"))
                .seasonConceded(object.getInt("seasonConceded"))
                .seasonGoalDifference(object.getInt("seasonGoalDifference"))
                .matchesPlayed(object.getInt("matchesPlayed"))
                .corrections(object.getInt("corrections"))
                .shortHand(object.get("shortHand").toString())
                .wdlRecord(object.get("wdl_record").toString())
                .country(COUNTRY)
                .cleanName(object.get("cleanName").toString())
                .seasonWinsOverall(object.getInt("seasonWins_overall"))
                .seasonDrawsOverall(object.getInt("seasonDraws_overall"))
                .seasonLossesOverall(object.getInt("seasonLosses_overall"))
                .seasonWinsHome(object.getInt("seasonWins_home"))
                .seasonDrawsHome(object.getInt("seasonDraws_home"))
                .seasonLossesHome(object.getInt("seasonLosses_home"))
                .seasonGoalsHome(object.getInt("seasonGoals_home"))
                .seasonConcededHome(object.getInt("seasonConceded_home"))
                .seasonWinsAway(object.getInt("seasonWins_away"))
                .seasonDrawsAway(object.getInt("seasonDraws_away"))
                .seasonLossesAway(object.getInt("seasonLosses_away"))
                .seasonGoalsAway(object.getInt("seasonGoals_away"))
                .seasonConcededAway(object.getInt("seasonConceded_away"))
                .build();
    }

    private SeasonDetails updateSeasonDetails(SeasonDetails seasonDetails, JSONObject object){
        return SeasonDetails.builder()
                .id(seasonDetails.getId())
                .season(seasonDetails.getSeason())
                .club(seasonDetails.getClub())
                .position(object.getInt("position"))
                .points(object.getInt("points"))
                .seasonGoals(object.getInt("seasonGoals"))
                .seasonConceded(object.getInt("seasonConceded"))
                .seasonGoalDifference(object.getInt("seasonGoalDifference"))
                .matchesPlayed(object.getInt("matchesPlayed"))
                .corrections(object.getInt("corrections"))
                .shortHand(object.get("shortHand").toString())
                .wdlRecord(object.get("wdl_record").toString())
                .country(COUNTRY)
                .cleanName(object.get("cleanName").toString())
                .seasonWinsOverall(object.getInt("seasonWins_overall"))
                .seasonDrawsOverall(object.getInt("seasonDraws_overall"))
                .seasonLossesOverall(object.getInt("seasonLosses_overall"))
                .seasonWinsHome(object.getInt("seasonWins_home"))
                .seasonDrawsHome(object.getInt("seasonDraws_home"))
                .seasonLossesHome(object.getInt("seasonLosses_home"))
                .seasonGoalsHome(object.getInt("seasonGoals_home"))
                .seasonConcededHome(object.getInt("seasonConceded_home"))
                .seasonWinsAway(object.getInt("seasonWins_away"))
                .seasonDrawsAway(object.getInt("seasonDraws_away"))
                .seasonLossesAway(object.getInt("seasonLosses_away"))
                .seasonGoalsAway(object.getInt("seasonGoals_away"))
                .seasonConcededAway(object.getInt("seasonConceded_away"))
                .build();
    }
}
