package com.tse.spring_boot_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tse.spring_boot_project.entities.Team;
import com.tse.spring_boot_project.repositories.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTeam_Endpoint() throws Exception {
        // GIVEN
        Team team = new Team("ControllerTeam", "ControllerCity");

        // WHEN
        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.name").value("ControllerTeam"));

        // THEN
        var allTeams = teamRepository.findAll();
        Assertions.assertFalse(allTeams.isEmpty());
    }

   
}
