package com.tse.spring_boot_project.controllers;

import com.tse.spring_boot_project.entities.Team;
import com.tse.spring_boot_project.repositories.TeamRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TeamMvcController {

    @Autowired
    private TeamRepository teamRepository;

    // Endpoint pour afficher toutes les équipes avec leurs joueurs
    @GetMapping("/teamsPage")
    public String getTeams(Model model) {
        // Récupérer toutes les équipes depuis le repository
        List<Team> teams = teamRepository.findAllWithPlayers(); 
        model.addAttribute("teams", teams);
        return "teams"; // Nom que j'ai appelé la template Thymeleaf (teams.html)
    }
}
