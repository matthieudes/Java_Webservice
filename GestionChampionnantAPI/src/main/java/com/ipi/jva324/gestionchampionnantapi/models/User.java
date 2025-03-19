package com.ipi.jva324.gestionchampionnantapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull(message = "le champ firstName ne peut pas être null")
    @NotBlank(message = "le champ firstName ne peut pas être vide")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "le champ lastName ne peut pas être null")
    @NotBlank(message = "le champ lastName ne peut pas être vide")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "le champ email ne peut pas être null")
    @NotBlank(message = "le champ email ne peut pas être vide")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "le champ password ne peut pas être null")
    @NotBlank(message = "le champ password ne peut pas être vide")
    private String password;

    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = bCryptPasswordEncoder.encode(password);
    }

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "le champ firstName ne peut pas être null") @NotBlank(message = "le champ firstName ne peut pas être vide") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "le champ firstName ne peut pas être null") @NotBlank(message = "le champ firstName ne peut pas être vide") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "le champ lastName ne peut pas être null") @NotBlank(message = "le champ lastName ne peut pas être vide") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "le champ lastName ne peut pas être null") @NotBlank(message = "le champ lastName ne peut pas être vide") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "le champ email ne peut pas être null") @NotBlank(message = "le champ email ne peut pas être vide") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "le champ email ne peut pas être null") @NotBlank(message = "le champ email ne peut pas être vide") String email) {
        this.email = email;
    }

    public @NotNull(message = "le champ password ne peut pas être null") @NotBlank(message = "le champ password ne peut pas être vide") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "le champ password ne peut pas être null") @NotBlank(message = "le champ password ne peut pas être vide") String password) {
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }
}
