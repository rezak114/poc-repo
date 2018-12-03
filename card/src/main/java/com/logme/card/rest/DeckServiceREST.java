package com.logme.card.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logme.card.exception.FunctionalException;
import com.logme.card.service.DeckService;

@RestController
public class DeckServiceREST {

	@Autowired
	private DeckService deckService;

	@RequestMapping(value = "/games/decks", method = RequestMethod.POST)
	public ResponseEntity<String> createDeck() {
		return deckService.createDeck().map(e -> new ResponseEntity<>(e.getId().toString(), HttpStatus.OK))
				.orElse(new ResponseEntity<>("No deck created !!!", HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/games/{gameId}/decks/{deckId}", method = RequestMethod.PUT)
	public ResponseEntity<String> addDeck(@PathVariable("gameId") final long gameId, @PathVariable("deckId") final long deckId) {
		try {
			deckService.addDeck(deckId, gameId);
			return new ResponseEntity<>(String.format("the deck {} had been added to the game gameId ={}", deckId, gameId),
					HttpStatus.ACCEPTED);
		} catch (final FunctionalException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.ACCEPTED);
		}
	}

}
