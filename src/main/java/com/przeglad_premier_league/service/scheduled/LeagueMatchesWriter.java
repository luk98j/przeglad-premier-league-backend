package com.przeglad_premier_league.service.scheduled;

import com.przeglad_premier_league.model.club.Club;
import com.przeglad_premier_league.model.season.LeagueMatches;
import com.przeglad_premier_league.model.season.SeasonPeriod;
import com.przeglad_premier_league.repository.LeagueMatchesRepository;
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

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableAsync
public class LeagueMatchesWriter {

    private final LeagueMatchesRepository leagueMatchesRepository;
    private final FootballAPIService footballAPIService;
    private final ClubService clubService;
    private final SeasonPeriodService seasonPeriodService;
    @Value("${ppl.fdapi.key}")
    private String apiKey;
    @Value("${ppl.season.id}")
    private String seasonId;

    @Transactional
    @Async
    //@Scheduled(cron = "0 0/10 * * * ?")
    @Scheduled(cron = "0 0 4 * * *")
    public void saveMatches(){
        JSONArray jsonArray = footballAPIService.getLeagueMatchesDetails(apiKey, seasonId);
        JSONObject jsonObject;
        for(int i=0; i<jsonArray.length();i++){
            log.info("Parsing match number: {}",i);
            jsonObject = jsonArray.getJSONObject(i);
            if(!isLeagueMatchExists(String.valueOf(jsonObject.getInt("id")))){
                LeagueMatches leagueMatches = leagueMatchesBuilder(jsonObject);
                leagueMatchesRepository.save(leagueMatches);
            }
        }
    }

    private LeagueMatches leagueMatchesBuilder(JSONObject jsonObject){
        return LeagueMatches.builder()
                .matchId(String.valueOf(jsonObject.getInt("id")))
                .homeId(findClub(jsonObject.getString("home_name")))
                .awayId(findClub(jsonObject.getString("away_name")))
                .seasonId(getSeasonPeriod(jsonObject.getString("season")))
                .status(jsonObject.getString("status"))
                .roundId(String.valueOf(jsonObject.getInt("roundID")))
                .gameWeek(String.valueOf(jsonObject.getInt("game_week")))
                .homeGoals(String.valueOf(jsonObject.getInt("homeGoalCount")))
                .awayGoals(String.valueOf(jsonObject.getInt("awayGoalCount")))
                .homeGoalCount(jsonObject.getInt("homeGoalCount"))
                .awayGoalCount(jsonObject.getInt("awayGoalCount"))
                .teamHomeCorners(jsonObject.getInt("team_a_corners"))
                .teamAwayCorners(jsonObject.getInt("team_b_corners"))
                .teamAOffsides(jsonObject.getInt("team_a_offsides"))
                .teamBOffsides(jsonObject.getInt("team_b_offsides"))
                .teamAYellowCards(jsonObject.getInt("team_a_yellow_cards"))
                .teamBYellowCards(jsonObject.getInt("team_b_yellow_cards"))
                .teamARedCards(jsonObject.getInt("team_a_red_cards"))
                .teamBRedCards(jsonObject.getInt("team_a_red_cards"))
                .teamAShotsOnTarget(jsonObject.getInt("team_a_shotsOnTarget"))
                .teamBShotsOnTarget(jsonObject.getInt("team_b_shotsOnTarget"))
                .teamAShotsOffTarget(jsonObject.getInt("team_a_shotsOffTarget"))
                .teamBShotsOffTarget(jsonObject.getInt("team_b_shotsOffTarget"))
                .teamAShots(jsonObject.getInt("team_a_shots"))
                .teamBShots(jsonObject.getInt("team_b_shots"))
                .teamAFouls(jsonObject.getInt("team_a_fouls"))
                .teamBFouls(jsonObject.getInt("team_b_fouls"))
                .teamAPossession(jsonObject.getInt("team_a_possession"))
                .teamBPossession(jsonObject.getInt("team_b_possession"))
                .stadiumName(jsonObject.getString("stadium_name"))
                .homeName(jsonObject.getString("home_name"))
                .awayName(jsonObject.getString("away_name"))
                .build();

    }

    private Club findClub(String clubName){
        return clubService.getClubForLeagueMatches(clubName);
    }

    private SeasonPeriod getSeasonPeriod(String period){
        return seasonPeriodService.getSeasonPeriod(period);
    }

    private boolean isLeagueMatchExists(String matchId){
        List<LeagueMatches> list = leagueMatchesRepository.findByMatchId(matchId);
        if(list.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

}
