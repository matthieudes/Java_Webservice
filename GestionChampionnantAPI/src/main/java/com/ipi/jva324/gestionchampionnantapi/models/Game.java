package com.ipi.jva324.gestionchampionnantapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull
    private int team1Point;

    @Column(nullable = false)
    @NotNull
    private int team2Point;


    @ManyToOne
    @JoinColumn(name = "idTeam1")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "idTeam2")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "idDay")
    private Day day;

    public Game() {
    }

    public Game(int team1Point, int team2Point, Team team1, Team team2, Day day) {
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.team1 = team1;
        this.team2 = team2;
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public int getTeam1Point() {
        return team1Point;
    }

    public void setTeam1Point(@NotNull int team1Point) {
        this.team1Point = team1Point;
    }

    @NotNull
    public int getTeam2Point() {
        return team2Point;
    }

    public void setTeam2Point(@NotNull int team2Point) {
        this.team2Point = team2Point;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
