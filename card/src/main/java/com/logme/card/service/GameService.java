package com.logme.card.service;

import java.util.List;
import java.util.Optional;

import com.logme.card.dto.UserDTO;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.exception.FunctionalException;

public interface GameService {

	Optional<Game> createGame();
	
	void deleteGame(Long gameId);
	
	Optional<CardInfo> dealCard(Long gameId,String login) throws FunctionalException;

	void shuffle(Long gameId);
	
	List<UserDTO> getUsersByScoreDesc(Long gameId) throws FunctionalException;
}
