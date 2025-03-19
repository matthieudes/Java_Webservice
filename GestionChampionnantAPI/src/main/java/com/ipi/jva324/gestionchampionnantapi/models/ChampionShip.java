package com.ipi.jva324.gestionchampionnantapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
public class ChampionShip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull(message = "le champ name ne peut pas être null")
    @NotBlank(message = "le champ name ne peut pas être vide")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "le champ startDate ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(nullable = false)
    @NotNull(message = "le champ endDate ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(nullable = false)
    @NotNull(message = "le champ wonPoint ne peut pas être null")
    private int wonPoint;

    @Column(nullable = false)
    @NotNull(message = "le champ lostPoint ne peut pas être null")
    private int lostPoint;

    @Column(nullable = false)
    @NotNull(message = "le champ drawPoint ne peut pas être null")
    private int drawPoint;


    public ChampionShip() {
    }

    public ChampionShip(String name, Date startDate, Date endDate, int wonPoint,
                        int lostPoint, int drawPoint) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wonPoint = wonPoint;
        this.lostPoint = lostPoint;
        this.drawPoint = drawPoint;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "le champ name ne peut pas être null") @NotBlank(message = "le champ name ne peut pas être vide") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "le champ name ne peut pas être null") @NotBlank(message = "le champ name ne peut pas être vide") String name) {
        this.name = name;
    }

    public @NotNull(message = "le champ startDate ne peut pas être null") Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "le champ startDate ne peut pas être null") Date startDate) {
        this.startDate = startDate;
    }

    public @NotNull(message = "le champ endDate ne peut pas être null") Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "le champ endDate ne peut pas être null") Date endDate) {
        this.endDate = endDate;
    }

    @NotNull(message = "le champ wonPoint ne peut pas être null")
    public int getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(@NotNull(message = "le champ wonPoint ne peut pas être null") int wonPoint) {
        this.wonPoint = wonPoint;
    }

    @NotNull(message = "le champ lostPoint ne peut pas être null")
    public int getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(@NotNull(message = "le champ lostPoint ne peut pas être null") int lostPoint) {
        this.lostPoint = lostPoint;
    }

    @NotNull(message = "le champ drawPoint ne peut pas être null")
    public int getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(@NotNull(message = "le champ drawPoint ne peut pas être null") int drawPoint) {
        this.drawPoint = drawPoint;
    }
}
