package com.ipi.jva324.gestionchampionnantapi.repository;

import com.ipi.jva324.gestionchampionnantapi.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findTeamByChampionShipsId(Long IdchampionShip);
    @Override
    List<Team> findAll();

    Team findById(long id);
}
