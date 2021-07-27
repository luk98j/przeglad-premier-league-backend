package com.przeglad_premier_league.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeasonDetailsDTO {
    private String clubClubName;
    private String shortHand;
    private int position;
    private int points;
    private int seasonGoals;
    private int seasonConceded;
    private int seasonGoalDifference;
    private int matchesPlayed;
    private String wdlRecord;
    private int seasonWinsOverall;
    private int seasonDrawsOverall;
    private int seasonLossesOverall;
    private int seasonWinsHome;
    private int seasonDrawsHome;
    private int seasonLossesHome;
    private int seasonGoalsHome;
    private int seasonConcededHome;
    private int seasonWinsAway;
    private int seasonDrawsAway;
    private int seasonLossesAway;
    private int seasonGoalsAway;
    private int seasonConcededAway;
}
