package com.tse.spring_boot_project.repositories;

import com.tse.spring_boot_project.entities.Player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

List<Player> findByTeamId(Long teamId);

List<Player> findAllByOrderByTeamIdAsc();


}
