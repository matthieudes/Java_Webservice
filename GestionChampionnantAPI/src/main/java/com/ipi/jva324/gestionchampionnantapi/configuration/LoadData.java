package com.ipi.jva324.gestionchampionnantapi.configuration;

import com.ipi.jva324.gestionchampionnantapi.models.User;
import com.ipi.jva324.gestionchampionnantapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
public class LoadData {

    private final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) throws ParseException {
        log.info("Initialisation de la base de données");

        if (userRepository.count() == 0) {
            log.info("Ajout de l'utilisateur");
            User user = new User("John", "Doe", "johnDoe@gmail.com", "password");

            return args -> {
                log.info("Ajout de l'utilisateur{}", userRepository.save(user));
            };
        } else {
            return args -> {
                log.info("La base de données contient déjà des utilisateurs");
            };
        }
    }
}
