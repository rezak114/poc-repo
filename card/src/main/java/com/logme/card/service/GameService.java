package com.logme.card.service;

import java.util.List;
import java.util.Optional;

import com.logme.card.dto.PlayerResultDTO;
import com.logme.card.dto.SuitCountDTO;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.entity.Player;
import com.logme.card.exception.FunctionalException;

public interface GameService {

	/**
	 * Create a game.
	 *
	 * @return an Optional of {@link Game}.
	 */
	Optional<Game> createGame();

	/**
	 * Delete a game if exists.
	 *
	 * @param gameId the identifier of a game.
	 */
	void deleteGame(Long gameId);

	/**
	 * Retrieve a card from the remaining gards in game.
	 *
	 * @param gameId the identifier of a game.
	 * @param login  the login of the {@link Player}.
	 * @return {@link CardInfo} if found, or an empty {@link Optional} if not.
	 * @throws FunctionalException if game or user is not found.
	 */
	Optional<CardInfo> dealCard(Long gameId, String login) throws FunctionalException;

	/**
	 * Shuffle a game.
	 *
	 * @param gameId the identifier of a game.
	 */
	void shuffle(Long gameId);

	/**
	 * List the user's of game sorted by the player having the highest score to the
	 * lowest one.
	 *
	 * @param gameId the identifier of game.
	 * @return
	 * @throws FunctionalException
	 */
	List<PlayerResultDTO> getUsersByScoreDesc(Long gameId) throws FunctionalException;

	/**
	 * Get the number of remaining card grouped by the suit.
	 *
	 * @param gameId the identifier of game.
	 * @return
	 * @throws FunctionalException
	 */
	Optional<SuitCountDTO> getSuitCount(Long gameId) throws FunctionalException;
}
