package com.logme.card.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import com.logme.card.CardApplicationTests;
import com.logme.card.entity.Deck;
import com.logme.card.exception.FunctionalException;

@Rollback(value = true)
@Sql("classpath:deck-service/create-game.sql")
public class DeckServiceTest extends CardApplicationTests {

	private static Long GAME_ID = 53l;

	private static Long DECK_ID = 53l;

	private static Long DECK_ID_WITh_GAME = 54l;

	@Autowired
	private DeckService deckService;

	@Test
	public void testAddOK() throws FunctionalException {
		final Optional<Deck> deck = this.deckService.addDeck(DECK_ID, GAME_ID);
		Assertions.assertThat(deck.isPresent()).isTrue();
		Assertions.assertThat(deck.get().getGame().getId()).isEqualTo(GAME_ID);
	}

	@Test
	public void testAddKO() throws FunctionalException {
		final Optional<Deck> deck = this.deckService.addDeck(DECK_ID_WITh_GAME, DECK_ID);
		Assertions.assertThat(deck.isPresent()).isFalse();
	}

	@Test
	public void testCreateOK() {
		final Optional<Deck> deck = this.deckService.createDeck();
		Assertions.assertThat(deck.isPresent()).isTrue();
		Assertions.assertThat(deck.get().getCards()).isNotEmpty().hasSize(52);
		Assertions.assertThat(deck.get().getGame()).isNull();
	}

}
