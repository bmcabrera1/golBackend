package com.example.golbackend.modules.championship_managment.services;


import com.example.golbackend.modules.championship_managment.exception.ChampionshipNotFoundException;
import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.repositories.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;


    public Championship createChampionship(Championship championship) {
        return championshipRepository.save(championship);
    }

    public List<Championship> listChampionship(){
        return championshipRepository.findAll();
    }

    public Championship getChampionshipById(Long id){
        return championshipRepository.findById(id)
                .orElseThrow(() -> new ChampionshipNotFoundException(id));
    }

    public Championship updateChampionship(Long id, Championship championshipDetails) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ChampionshipNotFoundException(id));

            if (championshipDetails.getChampionshipName() != null)
                championship.setChampionshipName(championshipDetails.getChampionshipName());

            if (championshipDetails.getChampionshipDescription() != null)
                championship.setChampionshipDescription(championshipDetails.getChampionshipDescription());

            if (championshipDetails.getStartDate() != null)
                championship.setStartDate(championshipDetails.getStartDate());

            if (championshipDetails.getEndDate() != null)
                championship.setEndDate(championshipDetails.getEndDate());

            if (championshipDetails.getChampionshipStatus() != null)
                championship.setChampionshipStatus(championshipDetails.getChampionshipStatus());

            if (championshipDetails.getMinPlayersField() != 0)
                championship.setMinPlayersField(championshipDetails.getMinPlayersField());

            if (championshipDetails.getMinPlayersRegistered() != 0)
                championship.setMinPlayersRegistered(championshipDetails.getMinPlayersRegistered());

            if (championshipDetails.getMaxPlayersRegistered() != 0)
                championship.setMaxPlayersRegistered(championshipDetails.getMaxPlayersRegistered());

            if (championshipDetails.getYellowDoubleSuspensionMatches() != 0)
                championship.setYellowDoubleSuspensionMatches(championshipDetails.getYellowDoubleSuspensionMatches());

            if (championshipDetails.getRedSuspensionMatches() != 0)
                championship.setRedSuspensionMatches(championshipDetails.getRedSuspensionMatches());

            if (championshipDetails.getYellowAccumulationSuspensionMatches() != 0)
                championship.setYellowAccumulationSuspensionMatches(championshipDetails.getYellowAccumulationSuspensionMatches());

            if (championshipDetails.getYellowAccumulationNumber() != 0)
                championship.setYellowAccumulationNumber(championshipDetails.getYellowAccumulationNumber());

            if (championshipDetails.getRule() != null)
                championship.setRule(championshipDetails.getRule());

            if (championshipDetails.getFirstPlacePrize() != null)
                championship.setFirstPlacePrize(championshipDetails.getFirstPlacePrize());

            if (championshipDetails.getSecondPlacePrize() != null)
                championship.setSecondPlacePrize(championshipDetails.getSecondPlacePrize());

            if (championshipDetails.getThirdPlacePrize() != null)
                championship.setThirdPlacePrize(championshipDetails.getThirdPlacePrize());

            if (championshipDetails.getOtherPlacePrize() != null)
                championship.setOtherPlacePrize(championshipDetails.getOtherPlacePrize());

            if (championshipDetails.getPointsWin() != 0)
                championship.setPointsWin(championshipDetails.getPointsWin());

            if (championshipDetails.getPointsLose() != 0)
                championship.setPointsLose(championshipDetails.getPointsLose());

            if (championshipDetails.getPointsDraw() != 0)
                championship.setPointsDraw(championshipDetails.getPointsDraw());

            return championshipRepository.save(championship);
    }

    public void deleteChampionship(Long id) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ChampionshipNotFoundException(id));
        championshipRepository.delete(championship);
    }






}
