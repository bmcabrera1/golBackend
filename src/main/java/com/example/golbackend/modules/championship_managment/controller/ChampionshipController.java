package com.example.golbackend.modules.championship_managment.controller;


import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.services.ChampionshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Championship>> getChampionships() {
        List<Championship> championshipList = championshipService.listChampionship();
        return ResponseEntity.ok(championshipList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Championship> getChampionshipByID(@PathVariable Long id) {
        Championship championship = championshipService.getChampionshipById(id);
        return ResponseEntity.ok(championship);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Championship> patchChampionship(
            @PathVariable Long id,
            @RequestBody Championship championshipDetails) {

        Championship updatedChampionship = championshipService.updateChampionship(id, championshipDetails);
        return ResponseEntity.ok(updatedChampionship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChampionship(@PathVariable Long id) {
        championshipService.deleteChampionship(id);
        return ResponseEntity.noContent().build();
    }


}
