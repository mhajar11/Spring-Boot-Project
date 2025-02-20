package com.tse.spring_boot_project.repositories;

import com.tse.spring_boot_project.entities.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase // Par défaut, replace=ANY => use H2
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void testCreateTeam() {
        // GIVEN
        Team team = new Team("TestName", "TestCity");

        // WHEN
        Team saved = teamRepository.save(team);

        // THEN
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("TestName", saved.getName());
        Assertions.assertEquals("TestCity", saved.getCity());
    }

    @Test
    void testFindById() {
        // GIVEN
        Team t = teamRepository.save(new Team("FindMe", "CityX"));

        // WHEN
        var found = teamRepository.findById(t.getId());

        // THEN
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("FindMe", found.get().getName());
    }
}
