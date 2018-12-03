package com.logme.card.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.logme.card.CardApplicationTests;
import com.logme.card.entity.Game;

@Rollback(value = true)
public class GameServiceTest extends CardApplicationTests {

	@Autowired
	private GameService gameService;

	@Test
	public void testCreate() {
		final Optional<Game> game = this.gameService.createGame();
		Assertions.assertThat(game.isPresent()).isTrue();
		Assertions.assertThat(game.get().getId()).isNotNull();
	}

}
