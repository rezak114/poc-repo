package com.logme.card.rest;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logme.card.entity.Card;
import com.logme.card.service.PlayerService;

@RestController
public class PlayerServiceREST {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/games/{gameId}/players/{login}/cards", method = RequestMethod.POST)
	public ResponseEntity<Set<Card>> getCards(@PathVariable("gameId") long gameId,
			@PathVariable("login") String login) {
		// FIXME gameId is not used !!!!!!!!!!!!
		Optional<Set<Card>> cards = playerService.getCards(login);
		return new ResponseEntity<>(cards.get(), HttpStatus.ACCEPTED);
	}

}
