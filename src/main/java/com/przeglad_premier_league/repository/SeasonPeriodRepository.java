package com.przeglad_premier_league.repository;

import com.przeglad_premier_league.model.season.SeasonPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonPeriodRepository extends JpaRepository<SeasonPeriod, Long> {
    List<SeasonPeriod> findByPeriod(String period);
}
