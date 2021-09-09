package com.przeglad_premier_league.model.club;

import com.przeglad_premier_league.model.season.LeagueMatches;
import com.przeglad_premier_league.model.season.SeasonDetails;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "club_table")
@Builder
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clubName;
    @OneToMany(mappedBy = "club")
    private Set<SeasonDetails> seasonStats;
    @OneToMany(mappedBy = "homeId")
    private Set<LeagueMatches> homeId;
    @OneToMany(mappedBy = "awayId")
    private Set<LeagueMatches> awayId;
}
