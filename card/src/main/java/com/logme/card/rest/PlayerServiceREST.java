package com.logme.card.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logme.card.entity.CardInfo;
import com.logme.card.service.PlayerService;

@RestController
public class PlayerServiceREST {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/games/{gameId}/players/{login}/cards", method = RequestMethod.GET)
	public ResponseEntity<List<CardInfo>> getCards(@PathVariable("gameId") final long gameId, @PathVariable("login") final String login) {
		// FIXME gameId is not used !!!!!!!!!!!!
		return new ResponseEntity<>(playerService.getCards(login).get(), HttpStatus.ACCEPTED);
	}

}
