package com.logme.card.service;

import java.util.Optional;
import java.util.Set;

import com.logme.card.entity.Card;
import com.logme.card.entity.Player;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;
import com.logme.card.exception.FunctionalException;

public interface PlayerService {

	Optional<Player> addPlayer(Long gameId, String login) throws FunctionalException;

	boolean removePlayer(Long gameId, Long playId) throws FunctionalException;

	Optional<Set<Card>> getCards(String login);
	
	void removeCard(String login, SuitEnum suitEnum, FaceEnum faceEnum);
	
	boolean addCard(String login, SuitEnum suitEnum, FaceEnum faceEnum);
	
}
