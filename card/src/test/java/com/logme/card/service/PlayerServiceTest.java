package com.logme.card.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import com.logme.card.CardApplicationTests;
import com.logme.card.entity.Game;
import com.logme.card.entity.Player;
import com.logme.card.exception.FunctionalException;
import com.logme.card.repository.GameRepository;

@Rollback(value = true)
@Sql("classpath:player-service/add-player.sql")
public class PlayerServiceTest extends CardApplicationTests {

	private static Long GAME_ID_NOT_EXISTING = 2222222l;

	private static Long GAME_ID = 100l;

	private static Long PLAYER_ID = 100l;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameRepository gameRepository;

	@Test(expected = FunctionalException.class)
	public void testAddKO() throws FunctionalException {
		playerService.addPlayer(100l, "rezak");
	}

	@Test
	public void testAddOK() throws FunctionalException {
		final Optional<Player> player = playerService.addPlayer(100l, "rezakNotExist");
		Assertions.assertThat(player.isPresent());
	}

	// The game is not existing
	@Test(expected = FunctionalException.class)
	public void removePlayer() throws FunctionalException {
		playerService.removePlayer(GAME_ID_NOT_EXISTING, "rezak");
	}

	// The game is not existing
	@Test(expected = FunctionalException.class)
	public void removePlayerIdNullKO() throws FunctionalException {
		playerService.removePlayer(GAME_ID_NOT_EXISTING, null);
	}

	@Test
	public void removePlayerOK() throws FunctionalException {
		playerService.removePlayer(GAME_ID, "rezak");
		// We check that the game is really updated
		final Optional<Game> game = gameRepository.findById(GAME_ID);
		Assertions.assertThat(game.get().getPlayers()).isEmpty();
	}
}
