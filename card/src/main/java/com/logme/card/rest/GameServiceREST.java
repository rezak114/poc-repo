package com.logme.card.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logme.card.dto.CardCountResultDTO;
import com.logme.card.dto.PlayerResultDTO;
import com.logme.card.dto.SuitCountDTO;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.service.GameService;
import com.logme.card.service.PlayerService;

@RestController
public class GameServiceREST {

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/games", method = RequestMethod.POST)
	public ResponseEntity<Long> games() {
		final Optional<Game> game = gameService.createGame();
		if (!game.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(game.get().getId(), HttpStatus.OK);
	}

	@RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteGame(@PathVariable("gameId") final long gameId) {
		try {
			gameService.deleteGame(gameId);
			return new ResponseEntity<>("the game had been deleted", HttpStatus.ACCEPTED);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("the game had not been deleted", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/games/{gameId}/players/{login}", method = RequestMethod.POST)
	public ResponseEntity<String> addPlayer(@PathVariable("gameId") final long gameId, @PathVariable("login") final String login) {
		try {
			playerService.addPlayer(gameId, login);
			return new ResponseEntity<>(String.format("the player %s had been added to the game gameId = %s", login, gameId),
					HttpStatus.ACCEPTED);
		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("the player had not been added " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/players/{login}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePlayer(@PathVariable("gameId") final long gameId, @PathVariable("login") final String login) {
		try {
			playerService.removePlayer(gameId, login);
			return new ResponseEntity<>("Player removed successfully", HttpStatus.ACCEPTED);
		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error when removing player", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/cards/players/{login}", method = RequestMethod.GET)
	public ResponseEntity<CardInfo> dealCard(@PathVariable("gameId") final long gameId, @PathVariable("login") final String login) {
		try {
			final Optional<CardInfo> card = gameService.dealCard(gameId, login);
			return card.map(e -> new ResponseEntity<>(e, HttpStatus.ACCEPTED)).orElse(new ResponseEntity<>(null, HttpStatus.ACCEPTED));
		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/players/sort", method = RequestMethod.GET)
	public ResponseEntity<List<PlayerResultDTO>> getUsersByScoreDesc(@PathVariable("gameId") final long gameId) {
		try {
			return new ResponseEntity<>(gameService.getUsersByScoreDesc(gameId), HttpStatus.ACCEPTED);

		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/shuffle", method = RequestMethod.POST)
	public ResponseEntity<String> shuffle(@PathVariable("gameId") final long gameId) {
		try {
			gameService.shuffle(gameId);
			return new ResponseEntity<>("cards shuffled successfully", HttpStatus.ACCEPTED);
		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("error when shuffling cards", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/suit", method = RequestMethod.GET)
	public ResponseEntity<SuitCountDTO> getSuitCount(@PathVariable("gameId") final long gameId) {
		try {
			gameService.getSuitCount(gameId);
			return new ResponseEntity<>(gameService.getSuitCount(gameId).get(), HttpStatus.ACCEPTED);
		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/countcards", method = RequestMethod.GET)
	public ResponseEntity<List<CardCountResultDTO>> getCardCountDTO(@PathVariable("gameId") final long gameId) {
		try {
			gameService.getSuitCount(gameId);
			return new ResponseEntity<>(gameService.getCardCountDTO(gameId).get(), HttpStatus.ACCEPTED);
		} catch (final Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
