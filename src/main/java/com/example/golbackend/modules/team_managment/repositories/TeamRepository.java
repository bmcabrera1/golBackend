package com.example.golbackend.modules.team_managment.repositories;

import com.example.golbackend.modules.team_managment.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
