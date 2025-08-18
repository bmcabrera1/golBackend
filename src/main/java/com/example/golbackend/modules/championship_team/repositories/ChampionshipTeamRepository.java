package com.example.golbackend.modules.championship_team.repositories;

import com.example.golbackend.modules.championship_team.model.ChampionshipTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionshipTeamRepository extends JpaRepository<ChampionshipTeam, Long> {
    List<ChampionshipTeam> findByChampionshipChampionshipId(Long championshipId);
    Optional<ChampionshipTeam> findByChampionshipChampionshipIdAndTeamId(Long championshipId, Long teamId);
}
