package com.przeglad_premier_league.model.season;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.przeglad_premier_league.model.club.Club;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "league_matches_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Access(AccessType.FIELD)
public class LeagueMatches implements Serializable {
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
    //@Column(name="team_a_corners")
    private int teamHomeCorners;
    //@Column(name="team_b_corners")
    private int teamAwayCorners;
    @Column(name="team_a_offsides")
    private int teamAOffsides;
    @Column(name="team_b_offsides")
    private int teamBOffsides;
    @Column(name="team_a_yellow_cards")
    private int teamAYellowCards;
    @Column(name="team_b_yellow_cards")
    private int teamBYellowCards;
    @Column(name="team_a_red_cards")
    private int teamARedCards;
    @Column(name="team_b_red_cards")
    private int teamBRedCards;
    @Column(name="team_a_shots_on_target")
    private int teamAShotsOnTarget;
    @Column(name="team_b_shots_on_target")
    private int teamBShotsOnTarget;
    @Column(name="team_a_shots_off_target")
    private int teamAShotsOffTarget;
    @Column(name="team_b_shots_off_target")
    private int teamBShotsOffTarget;
    @Column(name="team_a_shots")
    private int teamAShots;
    @Column(name="team_b_shots")
    private int teamBShots;
    @Column(name="team_a_fouls")
    private int teamAFouls;
    @Column(name="team_b_fouls")
    private int teamBFouls;
    @Column(name="team_a_possession")
    private int teamAPossession;
    @Column(name="team_b_possession")
    private int teamBPossession;
    private String stadiumName;
    private String homeName;
    private String awayName;

}
