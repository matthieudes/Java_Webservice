package com.ipi.jva324.gestionchampionnantapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String number;

    @ManyToOne
    @JoinColumn(name = "idchampionship")
    private ChampionShip championShip;

    public Day() {
    }

    public Day(String number, ChampionShip championShip) {
        this.number = number;
        this.championShip = championShip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getNumber() {
        return number;
    }

    public void setNumber(@NotNull @NotBlank String number) {
        this.number = number;
    }

    public ChampionShip getChampionnat() {
        return championShip;
    }

    public void setChampionnat(ChampionShip championShip) {
        this.championShip = championShip;
    }
}
