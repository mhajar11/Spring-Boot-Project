package com.tse.spring_boot_project.controllers;


import com.tse.spring_boot_project.entities.Team;
import com.tse.spring_boot_project.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    // 1. Récupérer toutes les teams
    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // 2. Récupérer une team par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Créer une nouvelle team
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    // 4. Mettre à jour une team existante
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        return teamRepository.findById(id)
                .map(team -> {
                    team.setName(updatedTeam.getName());
                    team.setCity(updatedTeam.getCity());
                    return ResponseEntity.ok(teamRepository.save(team));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Supprimer une team
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        var optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            teamRepository.delete(optionalTeam.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
