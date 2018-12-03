package com.logme.card.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.logme.card.entity.Game;

@Service
public interface GameRepository extends CrudRepository<Game, Long> {

}
