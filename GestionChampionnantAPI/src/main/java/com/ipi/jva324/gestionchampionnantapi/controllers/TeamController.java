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

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    public ChampionShipRepository championShipRepository;
    public TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository, ChampionShipRepository championShipRepository) {
        this.teamRepository = teamRepository;
        this.championShipRepository = championShipRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        teamRepository.findAll().forEach(teams::add);

        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/championship/{championshipId}/teams")
    public ResponseEntity<List<Team>> getAllTeamsByChampionShipId(@PathVariable(value = "championshipId") Long championshipId) {
        if (!championShipRepository.existsById(championshipId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ChampionShip non trouvé");
        }

        List<Team> teams = teamRepository.findTeamByChampionShipsId(championshipId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamsById(@PathVariable(value = "id") Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found Tag with id = " + id));

        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/{teamId}/championships")
    public ResponseEntity<List<ChampionShip>> getAllChampionShipsByTeamId(@PathVariable(value = "teamId") Long teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found Tag  with id = " + teamId);
        }

        List<ChampionShip> championShips = championShipRepository.findByTeamsId(teamId);
        return new ResponseEntity<>(championShips, HttpStatus.OK);
    }

    @PostMapping("/championships/{championshipId}/teams")
    public ResponseEntity<Team> addTeamToChampionShip(@PathVariable(value = "championshipId") Long championshipId, @RequestBody Team teamRequest) {
        Team team = championShipRepository.findById(championshipId).map(championShip -> {
            long teamId = teamRequest.getId();

            // team exist
            if (teamId != 0L) {
                Team _team = teamRepository.findById(teamId);
                if (_team == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Team with id = " + teamId);
                }else{
                    championShip.addTeam(_team);
                    championShipRepository.save(championShip);
                    return _team;
                }

            }

            // add and create new Tag
            championShip.addTeam(teamRequest);
            return teamRepository.save(teamRequest);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found Tutorial with id = " + championshipId));

        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTag(@PathVariable("id") long id, @RequestBody Team teamRequest) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Team with id = " + id);
        }
        team.setName(teamRequest.getName());

        return new ResponseEntity<>(teamRepository.save(team), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        teamRepository.save(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") long id) {
        teamRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
