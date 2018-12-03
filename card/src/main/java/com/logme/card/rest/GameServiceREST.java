package com.logme.card.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
		Optional<Game> game = gameService.createGame();
		if (!game.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(game.get().getId(), HttpStatus.OK);
	}

	@RequestMapping(value = "/games/{gameId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteGame(@PathVariable("gameId") long gameId) {
		try {
			gameService.deleteGame(gameId);
			return new ResponseEntity<>("the game had been deleted", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("the game had not been deleted", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/games/{gameId}/players/{login}", method = RequestMethod.POST)
	public ResponseEntity<String> addPlayer(@PathVariable("gameId") long gameId, @PathVariable("login") String login) {
		try {
			playerService.addPlayer(gameId, login);
			return new ResponseEntity<>(
					String.format("the player %s had been added to the game gameId = %s", login, gameId),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("the player had not been added " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/games/{gameId}/cards/players/{login}", method = RequestMethod.GET)
	public ResponseEntity<CardInfo> dealCard(@PathVariable("gameId") long gameId, @PathVariable("login") String login) {
		try {
			Optional<CardInfo> card = gameService.dealCard(gameId, login);
			return new ResponseEntity<>(card.get(),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

}
