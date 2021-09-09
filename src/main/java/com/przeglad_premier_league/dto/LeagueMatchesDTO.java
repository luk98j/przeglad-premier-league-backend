package com.przeglad_premier_league.dto;

import com.przeglad_premier_league.model.season.SeasonPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeagueMatchesDTO {
    private long id;
    private String matchId;
    private String homeIdClubName;
    private String awayIdClubName;
    private String seasonIdPeriod;
    private String status;
    private String roundId;
    private String gameWeek;
    private String homeGoals;
    private String awayGoals;
    private int homeGoalCount;
    private int awayGoalCount;
    private int teamHomeCorners;
    private int teamAwayCorners;
    private int teamAOffsides;
    private int teamBOffsides;
    private int teamAYellowCards;
    private int teamBYellowCards;
    private int teamARedCards;
    private int teamBRedCards;
    private int teamAShotsOnTarget;
    private int teamBShotsOnTarget;
    private int teamAShotsOffTarget;
    private int teamBShotsOffTarget;
    private int teamAShots;
    private int teamBShots;
    private int teamAFouls;
    private int teamBFouls;
    private int teamAPossession;
    private int teamBPossession;
    private String stadiumName;
    private String homeName;
    private String awayName;
}
