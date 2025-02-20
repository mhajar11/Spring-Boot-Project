package com.tse.spring_boot_project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int number;

    @ManyToOne
    @JsonBackReference
    private Team team;

    // Constructeur par défaut
    public Player() {
    }

    // Constructeur avec paramètres 
    public Player(String name, int number, Team team) {
        this.name = name;
        this.number = number;
        this.team = team;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Team getTeam() {
        return team;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
