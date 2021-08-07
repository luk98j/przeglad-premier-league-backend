package com.przeglad_premier_league.service;

import com.przeglad_premier_league.model.club.Club;
import com.przeglad_premier_league.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public Club getClubOrCreateNew(String clubName){
        List<Club> clubList = clubRepository.findByClubName(clubName);
        if(clubList.isEmpty()){
            Club club = Club.builder().clubName(clubName).build();
            clubRepository.save(club);
            return club;
        } else {
            return clubList.get(0);
        }
    }

    public Club getClubForLeagueMatches(String clubName){
        List<Club> clubList = clubRepository.findByClubNameContains(clubName);
        if(!clubList.isEmpty()){
            return clubList.get(0);
        } else {
            return null;
        }
    }
}
