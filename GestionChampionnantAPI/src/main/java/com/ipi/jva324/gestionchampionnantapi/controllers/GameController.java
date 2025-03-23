package com.ipi.jva324.gestionchampionnantapi.controllers;

import com.ipi.jva324.gestionchampionnantapi.models.Day;
import com.ipi.jva324.gestionchampionnantapi.models.Game;
import com.ipi.jva324.gestionchampionnantapi.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    public GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }



    @GetMapping("/games/{gamesId}")
    public ResponseEntity<Optional<Game>> getGameByDayId(@PathVariable("gamesId") long gamesId) {
        if (!gameRepository.existsById(gamesId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Résultat non trouvé");
        }
        Optional<Game> games = gameRepository.findById(gamesId);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Résultat non trouvé"));

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable("id") long id, @RequestBody Game game) {
        Game _game = gameRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Résultat non trouvé"));

        _game.setDay(game.getDay());
        _game.setTeam1(game.getTeam1());
        _game.setTeam2(game.getTeam2());
        _game.setTeam1Point(game.getTeam1Point());
        _game.setTeam2Point(game.getTeam2Point());
        return new ResponseEntity<>(gameRepository.save(_game), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable("id") long id) {
        gameRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
