package com.ipi.jva324.gestionchampionnantapi.configuration;

import com.ipi.jva324.gestionchampionnantapi.models.*;
import com.ipi.jva324.gestionchampionnantapi.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

@Configuration
public class LoadData {

    private final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, DayRepository dayRepository, ChampionShipRepository championShipRepository, TeamRepository teamRepository, GameRepository gameRepository) throws ParseException {
        log.info("Initialisation de la base de données");

        if (userRepository.count() == 0) {
            log.info("Ajout de l'utilisateur");
            User user = new User("John", "Doe", "johnDoe@gmail.com", "password");
            ChampionShip championShip = new ChampionShip("Championnat de France", new Date(2021-01-01), new Date(2021-12-31), 3, 0, 1);
            ChampionShip championShip2 = new ChampionShip("Championnat de Tokyo", new Date(2021-01-01), new Date(2021-12-31), 3, 0, 1);
            Team team = new Team("Reach Corp");
            Team team2 = new Team("Spartan Corp");
            Day day = new Day("1", championShip);
            Day day2 = new Day("3", championShip2);
            Game game = new Game( 2, 1, team,  team2, day);
            //championShip.addTeam(team);
            //championShip.addTeam(team2);
            return args -> {
                log.info("Ajout de l'utilisateur{}", userRepository.save(user));
                log.info("Ajout de l'utilisateur{}", championShipRepository.save(championShip));
                log.info("Ajout de l'utilisateur{}", championShipRepository.save(championShip2));
                log.info("Ajout de l'utilisateur{}", dayRepository.save(day));
                log.info("Ajout de l'utilisateur{}", dayRepository.save(day2));
                log.info("Ajout de l'utilisateur{}", teamRepository.save(team));
                log.info("Ajout de l'utilisateur{}", teamRepository.save(team2));
                log.info("Ajout de l'utilisateur{}", gameRepository.save(game));
            };
        } else {
            return args -> {
                log.info("La base de données contient déjà des utilisateurs");
            };
        }
    }
}
