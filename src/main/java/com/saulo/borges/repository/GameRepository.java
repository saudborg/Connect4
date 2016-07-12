package com.saulo.borges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saulo.borges.model.GameModel;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Integer> {

}
