package com.logme.card.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.logme.card.entity.Card;
import com.logme.card.entity.Deck;
import com.logme.card.entity.Game;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;
import com.logme.card.exception.FunctionalException;
import com.logme.card.repository.CardRepository;
import com.logme.card.repository.DeckRepository;
import com.logme.card.repository.GameRepository;
import com.logme.card.service.DeckService;

@Service
@Transactional
public class DeckServiceImpl implements DeckService {

	@Autowired
	private DeckRepository deckRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private CardRepository cardRepository;

	@Override
	public Optional<Deck> createDeck() {
		final Iterable<Card> cards = cardRepository.findAll();
		final Deck deck = new Deck();
		deck.setCards(Sets.newHashSet(cards));
		deckRepository.save(deck);
		return Optional.ofNullable(deck);
	}

	@Override
	public Optional<Deck> addDeck(final Long deckId, final Long gameId) throws FunctionalException {
		if (null == gameId || null == deckId) {
			throw new FunctionalException("Missing parameters to add deck");
		}
		final Optional<Deck> deck = deckRepository.findById(deckId);
		final Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		if (!deck.isPresent()) {
			throw new FunctionalException("Deck not found deckId= {}", deckId);
		}
		if (null != deck.get().getGame()) {
			return Optional.empty();
		}
		deck.get().setGame(game);
		deckRepository.save(deck.get());
		game.getCards().addAll(deck.get().getCards());
		gameRepository.save(game);
		return Optional.ofNullable(deck.get());
	}

	@Override
	public void removeCard(final SuitEnum suitEnum, final FaceEnum faceEnum) {
		// TODO Auto-generated method stub

	}

}
