package com.logme.card.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logme.card.dto.UserDTO;
import com.logme.card.entity.Card;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.exception.FunctionalException;
import com.logme.card.repository.GameRepository;
import com.logme.card.service.DeckService;
import com.logme.card.service.GameService;
import com.logme.card.service.PlayerService;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	private Map<Long, Game> games = new HashMap<>();

	@Autowired
	private PlayerService playerService;

	@Autowired
	private DeckService deckService;

	@Override
	public Optional<Game> createGame() {
		final Game game = new Game();
		return Optional.ofNullable(this.gameRepository.save(game));
	}

	@Override
	public void shuffle(Long gameId) {
		final Optional<Game> game = this.gameRepository.findById(gameId);
		if (!game.isPresent()) {
			return;
		}
		if (game.get().getDecks() != null) {
			final ArrayList<CardInfo> cards = new ArrayList<>();
			game.get().getDecks().forEach(deck -> {
				for (final Card card : deck.getCards()) {
					final CardInfo cardInfo = new CardInfo(deck.getId(), card.getSuit(), card.getFace());
					cards.add(cardInfo);
				}
			});
			if (cards.isEmpty() == false) {
				game.get().setCards(new LinkedList<>());
				for (int index = 0; index < cards.size(); index++) {
					final int randomIndex = (int) Math.random() * 100 % (cards.size() - index);
					game.get().getCards().add(cards.get(randomIndex));
					cards.remove(randomIndex);
				}
			}
			// We updated the game in current map
			games.put(gameId, game.get());
		}
	}

	@Override
	public void deleteGame(Long gameId) {
		gameRepository.deleteById(gameId);
	}

	@Override
	public Optional<CardInfo> dealCard(Long gameId, String login) throws FunctionalException {
		final Optional<Game> game = this.gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new FunctionalException("the game gameId {}  is not found", gameId);
		}
		if (games.get(gameId) == null) {
			// We call shuffle
			shuffle(gameId);
		}
		Queue<CardInfo> remainingCards = games.get(gameId).getCards();
		if (null == remainingCards || remainingCards.isEmpty()) {
			return Optional.empty();
		}
		CardInfo card = remainingCards.poll();
		// We add the card to the player
		playerService.addCard(login, card.getSuitEnum(), card.getFaceEnum());
		// We remove the card from the deck
		deckService.removeCard(card.getDeckId(), card.getSuitEnum(), card.getFaceEnum());
		return Optional.ofNullable(card);
	}

	@Override
	public List<UserDTO> getUsersByScoreDesc(Long gameId) throws FunctionalException{
		final Optional<Game> game = this.gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new FunctionalException("the game gameId {}  is not found", gameId);
		}
		game.get().getPlayers().forEach(player->{
			UserDTO userDTO=new UserDTO();
			userDTO.setId(player.getId());
			userDTO.setLogin(player.getLogin());
//			userDTO.setScore((Long)(player.getCards().stream().mapToInt(c->c.getFace().getPoint()).sum())));
		});
		return null;
	}

}
