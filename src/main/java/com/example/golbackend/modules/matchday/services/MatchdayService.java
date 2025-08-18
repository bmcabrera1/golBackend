package com.example.golbackend.modules.matchday.services;

import com.example.golbackend.modules.matchday.model.Matchday;
import com.example.golbackend.modules.matchday.repositories.MatchdayRepository;
import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.phase.repositories.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchdayService {

    @Autowired
    private MatchdayRepository matchdayRepository;
    @Autowired
    private PhaseRepository phaseRepository;

    public Matchday createMatchday(Long phaseId, Matchday matchday) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found with id: " + phaseId));
        matchday.setPhase(phase);
        return matchdayRepository.save(matchday);
    }

    public List<Matchday> getMatchdaysByPhase(Long phaseId) {
        return matchdayRepository.findByPhasePhaseIdOrderByMatchdayNumberAsc(phaseId);
    }
}