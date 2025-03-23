package com.ipi.jva324.gestionchampionnantapi.repository;

import com.ipi.jva324.gestionchampionnantapi.models.ChampionShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionShipRepository extends JpaRepository<ChampionShip, Long> {

    List<ChampionShip> findByTeamsId(Long teamId);
    @Override
    List<ChampionShip> findAll();
}
