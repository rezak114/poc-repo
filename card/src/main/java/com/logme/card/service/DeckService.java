package com.logme.card.service;

import java.util.Optional;

import com.logme.card.entity.Deck;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;
import com.logme.card.exception.FunctionalException;

public interface DeckService {

	Optional<Deck> createDeck();

	Optional<Deck> addDeck(Long deckId, Long gameId) throws FunctionalException;

	void removeCard(Long deckId, SuitEnum suitEnum, FaceEnum faceEnum);
}
