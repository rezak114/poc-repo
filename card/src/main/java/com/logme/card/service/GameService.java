package com.logme.card.service;

import java.util.List;
import java.util.Optional;

import com.logme.card.dto.PlayerResultDTO;
import com.logme.card.dto.SuitCountDTO;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.exception.FunctionalException;

public interface GameService {

	Optional<Game> createGame();

	void deleteGame(Long gameId);

	Optional<CardInfo> dealCard(Long gameId, String login) throws FunctionalException;

	void shuffle(Long gameId);

	List<PlayerResultDTO> getUsersByScoreDesc(Long gameId) throws FunctionalException;

	Optional<SuitCountDTO> getSuitCount(Long gameId) throws FunctionalException;
}
