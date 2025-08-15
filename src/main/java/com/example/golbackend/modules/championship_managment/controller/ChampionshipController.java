package com.example.golbackend.modules.championship_managment.controller;


import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.services.ChampionshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/championship")
public class ChampionshipController {

    @Autowired
    private ChampionshipService championshipService;

    @PostMapping
    public ResponseEntity<Championship> createChampionship(
        @Valid @RequestBody Championship championship){
        Championship saved =  championshipService.createChampionship(championship);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
