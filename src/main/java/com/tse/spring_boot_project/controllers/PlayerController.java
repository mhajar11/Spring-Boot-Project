package com.tse.spring_boot_project.controllers;

import com.tse.spring_boot_project.entities.Player;
import com.tse.spring_boot_project.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository PlayerRepository;

    // 1. Récupérer tous les joueurs
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return PlayerRepository.findAllByOrderByTeamIdAsc();
    }


    // 2. Récupérer un Joueur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return PlayerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Créer un nouveau Joueurs
    @PostMapping
    public Player createPlayer(@RequestBody Player Player) {
        return PlayerRepository.save(Player);
    }

    // 4. Mettre à jour un Player existant
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        return PlayerRepository.findById(id)
                .map(Player -> {
                    Player.setName(updatedPlayer.getName());
                    Player.setNumber(updatedPlayer.getNumber());
                    Player.setTeam(updatedPlayer.getTeam());
                    return ResponseEntity.ok(PlayerRepository.save(Player));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Supprimer une team
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        var optionalTeam = PlayerRepository.findById(id);
        if (optionalTeam.isPresent()) {
            PlayerRepository.delete(optionalTeam.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
