package com.logme.card.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logme.card.entity.Deck;
import com.logme.card.exception.FunctionalException;
import com.logme.card.service.DeckService;

@RestController
public class DeckServiceREST {

	@Autowired
	private DeckService deckService;

	@RequestMapping(value = "/games/decks", method = RequestMethod.POST)
	public ResponseEntity<String> createDeck() {
		Optional<Deck> deck = deckService.createDeck();
		if (!deck.isPresent()) {
			return new ResponseEntity<>("No deck created !!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(deck.get().getId().toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/games/{gameId}/decks/{deckId}", method = RequestMethod.POST)
	public ResponseEntity<String> addDeck(@PathVariable("gameId") long gameId,@PathVariable("deckId") long deckId) {
		try {
			deckService.addDeck(deckId, gameId);
			return new ResponseEntity<>(String.format("the deck {} had been added to the game gameId ={}",deckId,gameId), HttpStatus.ACCEPTED);
		} catch (FunctionalException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.ACCEPTED);
		}
	}

}
