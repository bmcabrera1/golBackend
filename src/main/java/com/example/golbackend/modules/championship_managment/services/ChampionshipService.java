package com.example.golbackend.modules.championship_managment.services;


import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.repositories.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository championshipRepository;


    public Championship createChampionship(Championship championship) {
        return championshipRepository.save(championship);
    }





}
