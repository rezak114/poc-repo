package com.logme.card.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.logme.card.entity.Player;

@Service
public interface PlayerRepository extends CrudRepository<Player, Long> {

	Optional<Player> findByLogin(String login);
}
