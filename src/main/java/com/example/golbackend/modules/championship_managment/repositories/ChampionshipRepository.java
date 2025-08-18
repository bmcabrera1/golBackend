package com.example.golbackend.modules.championship_managment.repositories;

import com.example.golbackend.modules.championship_managment.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository  extends JpaRepository<Championship, Long> {


}
