package com.saulo.borges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saulo.borges.model.PlayerModel;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Integer> {

}
