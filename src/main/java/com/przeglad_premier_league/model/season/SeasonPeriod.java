package com.przeglad_premier_league.model.season;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "season_period_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="period")
    private String period;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @OneToMany(mappedBy = "season")
    private Set<SeasonDetails> seasonStats;
    @OneToMany(mappedBy = "seasonId")
    private Set<LeagueMatches> seasonId;
}
