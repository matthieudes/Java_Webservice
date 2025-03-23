package com.ipi.jva324.gestionchampionnantapi.repository;

import com.ipi.jva324.gestionchampionnantapi.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {

    List<Day> findByChampionShipId(long championShipId);
    @Override
    List<Day> findAll();
}
