package com.example.golbackend.modules.match_event.repositories;

import com.example.golbackend.modules.match_event.model.MatchEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchEventRepository extends JpaRepository<MatchEvent, Long> {
    List<MatchEvent> findByMatchMatchIdOrderByEventMinuteAsc(Long matchId);
}