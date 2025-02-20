package com.tse.spring_boot_project.repositories;

import com.tse.spring_boot_project.entities.Player;
import com.tse.spring_boot_project.entities.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void testCreatePlayer() {
        // GIVEN
        Team team = new Team("TeamForPlayer", "TestCity");
        team = teamRepository.save(team);

        Player p = new Player();
        p.setName("Mbappe");
        p.setNumber(7);
        p.setTeam(team);

        // WHEN
        Player saved = playerRepository.save(p);

        // THEN
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Mbappe", saved.getName());
        Assertions.assertEquals(7, saved.getNumber());
        Assertions.assertEquals(team.getId(), saved.getTeam().getId());
    }
}
