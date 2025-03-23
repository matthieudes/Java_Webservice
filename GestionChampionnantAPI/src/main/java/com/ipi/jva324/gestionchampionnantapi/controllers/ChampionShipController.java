package com.ipi.jva324.gestionchampionnantapi.controllers;

import com.ipi.jva324.gestionchampionnantapi.models.ChampionShip;
import com.ipi.jva324.gestionchampionnantapi.models.Team;
import com.ipi.jva324.gestionchampionnantapi.repository.ChampionShipRepository;
import com.ipi.jva324.gestionchampionnantapi.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/championships")
public class ChampionShipController {
    public ChampionShipRepository championShipRepository;
    public TeamRepository teamRepository;
    public  ChampionShipController(ChampionShipRepository championShipRepository, TeamRepository teamRepository) {
        this.championShipRepository = championShipRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<ChampionShip>> getAllChampionShip() {
        List<ChampionShip> championShips = new ArrayList<>();

        championShipRepository.findAll().forEach(championShips::add);

        return new ResponseEntity<>(championShips, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionShip> getChampionShipById(@PathVariable("id") long id) {
        ChampionShip championShip = championShipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found ChampionShip with id = " + id));

        return new ResponseEntity<>(championShip, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ChampionShip> createChampionShip(@RequestBody ChampionShip championShip) {
        championShipRepository.save(championShip);
        return new ResponseEntity<>(championShip, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampionShip> updateChampionShip(@PathVariable("id") long id, @RequestBody ChampionShip championShip) {
        ChampionShip _championShip = championShipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found ChampionShip with id = " + id));

        _championShip.setName(championShip.getName());
        _championShip.setStartDate(championShip.getStartDate());
        _championShip.setEndDate(championShip.getEndDate());
        _championShip.setWonPoint(championShip.getWonPoint());
        _championShip.setLostPoint(championShip.getLostPoint());
        _championShip.setDrawPoint(championShip.getDrawPoint());
        _championShip.setTeams(championShip.getTeams());

        return new ResponseEntity<>(championShipRepository.save(_championShip), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChampionShip(@PathVariable("id") long id) {
        championShipRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{championshipId}/teams/{teamId}")
    public ResponseEntity<HttpStatus> deleteTeamFromChampionShip(@PathVariable(value = "championshipId") Long championshipId, @PathVariable(value = "teamId") Long teamId) {
        ChampionShip championShip = championShipRepository.findById(championshipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found ChampionShip with id = " + championshipId));

        championShip.removeTeam(teamId);
        championShipRepository.save(championShip);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
