package com.przeglad_premier_league.model.season;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.przeglad_premier_league.model.club.Club;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "league_matches_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LeagueMatches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String matchId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_id", nullable = false)
    private Club homeId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "away_id", nullable = false)
    private Club awayId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "season_id", nullable = false)
    private SeasonPeriod seasonId;

    private String status;
    private String roundId;
    private String gameWeek;
    private String homeGoals;
    private String awayGoals;
    private int homeGoalCount;
    private int awayGoalCount;
    private int teamACorners;
    private int teamBCorners;
    private int teamAOffsides;
    private int teamBOffsides;
    private int teamAYellow_cards;
    private int teamBYellow_cards;
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
