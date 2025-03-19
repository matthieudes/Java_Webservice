package com.ipi.jva324.gestionchampionnantapi.repository;

import com.ipi.jva324.gestionchampionnantapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByEmail(String email);
}
