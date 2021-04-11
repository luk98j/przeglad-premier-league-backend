package com.przeglad_premier_league.model.season;

import com.przeglad_premier_league.model.club.Club;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "season_details_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "season_id", nullable = false)
    private SeasonPeriod season;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;
    private int position;
    private int points;
    private int seasonGoals;
    private int seasonConceded;
    private int seasonGoalDifference;
    private int matchesPlayed;
    private int corrections;
    private String shortHand;
    private String wdlRecord;
    private String country;
    private String cleanName;
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
