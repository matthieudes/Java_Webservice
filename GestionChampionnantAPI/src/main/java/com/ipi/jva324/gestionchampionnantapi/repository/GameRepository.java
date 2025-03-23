package com.ipi.jva324.gestionchampionnantapi.repository;

import com.ipi.jva324.gestionchampionnantapi.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    @Override
    List<Game> findAll();
}
