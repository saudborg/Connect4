package com.saulo.borges.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saulo.borges.model.PlayerModel;

public interface PlayerRepository extends JpaRepository<PlayerModel, Integer> {

}
