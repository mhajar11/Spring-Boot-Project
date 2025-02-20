package com.tse.spring_boot_project.repositories;

import com.tse.spring_boot_project.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // Utilisation de fetch join pour récupérer les équipes avec leurs joueurs
    @Query("SELECT t FROM Team t LEFT JOIN FETCH t.players")
    List<Team> findAllWithPlayers();
}
