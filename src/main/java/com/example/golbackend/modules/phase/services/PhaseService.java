package com.example.golbackend.modules.phase.services;

import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.repositories.ChampionshipRepository;
import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.phase.repositories.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhaseService {

    @Autowired
    private PhaseRepository phaseRepository;
    @Autowired
    private ChampionshipRepository championshipRepository;

    public Phase createPhase(Long championshipId, Phase phase) {
        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new RuntimeException("Championship not found with id: " + championshipId));
        phase.setChampionship(championship);
        return phaseRepository.save(phase);
    }

    public List<Phase> getPhasesByChampionship(Long championshipId) {
        return phaseRepository.findByChampionshipChampionshipIdOrderByPhaseOrderAsc(championshipId);
    }
}

