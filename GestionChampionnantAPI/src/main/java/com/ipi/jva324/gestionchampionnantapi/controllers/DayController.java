package com.ipi.jva324.gestionchampionnantapi.controllers;

import com.ipi.jva324.gestionchampionnantapi.models.Day;
import com.ipi.jva324.gestionchampionnantapi.models.Team;
import com.ipi.jva324.gestionchampionnantapi.models.User;
import com.ipi.jva324.gestionchampionnantapi.repository.DayRepository;
import com.ipi.jva324.gestionchampionnantapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {
    public DayRepository dayRepository;
    public DayController(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @GetMapping("")
    public List<Day> getDays() {
        return dayRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable("id") long id) {
        Day day = dayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found Day with id = " + id));

        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    @GetMapping("/championships/{championshipId}")
    public ResponseEntity<List<Day>> getDayByChampionShipId(@PathVariable("championshipId") long championshipId) {
        if (!dayRepository.existsById(championshipId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Day non trouvé");
        }
        List<Day> days = dayRepository.findByChampionShipId(championshipId);
        return new ResponseEntity<>(days, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Day> createDay(@RequestBody Day day) {
        dayRepository.save(day);
        return new ResponseEntity<>(day, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable("id") long id, @RequestBody Day day) {
        Day _day = dayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found Day with id = " + id));

        _day.setNumber(day.getNumber());
        return new ResponseEntity<>(dayRepository.save(_day), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDay(@PathVariable("id") long id) {
        dayRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
