package com.ipi.jva324.gestionchampionnantapi.controllers;

import com.ipi.jva324.gestionchampionnantapi.models.User;
import com.ipi.jva324.gestionchampionnantapi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        //user.setCreationDate(new Date());
        userRepository.save(user);
        return new ResponseEntity<>(user , HttpStatus.CREATED);

    }

    @PutMapping("/{user}") // url:http://localhost:8080/api/users/1
    public ResponseEntity<User> updateUser(@PathVariable(name="user", required = false)User user,
                                           @Valid @RequestBody User userUpdate){
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }else {
            userUpdate.setId(user.getId());
            userRepository.save(userUpdate);
            return new ResponseEntity<>(userUpdate, HttpStatus.CREATED);

        }
    }

    @DeleteMapping(value = "/{user}")
    public void deleteUser(@PathVariable(name = "user", required = false)User user){
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }else {
            userRepository.delete(user);
        }
    }
}
