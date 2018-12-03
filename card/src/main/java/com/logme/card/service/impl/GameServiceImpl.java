package com.logme.card.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logme.card.dto.PlayerResultDTO;
import com.logme.card.dto.SuitCountDTO;
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

	private final Map<Long, Game> games = new HashMap<>();

	@Autowired
	private PlayerService playerService;

	@Autowired
	private DeckService deckService;

	@Override
	public Optional<Game> createGame() {
		final Game game = new Game();
		return Optional.ofNullable(gameRepository.save(game));
	}

	@Override
	public void shuffle(final Long gameId) {
		final Optional<Game> game = gameRepository.findById(gameId);
		if (!game.isPresent()) {
			return;
		}
		if (game.get().getDecks() != null) {
			final ArrayList<CardInfo> cards = new ArrayList<>();
			game.get().getDecks().forEach(deck -> {
				for (final Card card : deck.getCards()) {
					final CardInfo cardInfo = new CardInfo(card.getSuit(), card.getFace());
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
	public void deleteGame(final Long gameId) {
		gameRepository.deleteById(gameId);
	}

	@Override
	public Optional<CardInfo> dealCard(final Long gameId, final String login) throws FunctionalException {
		final Optional<Game> game = gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new FunctionalException("the game gameId {}  is not found", gameId);
		}
		if (games.get(gameId) == null) {
			// We call shuffle
			shuffle(gameId);
		}
		final Queue<CardInfo> remainingCards = games.get(gameId).getCards();
		if (null == remainingCards || remainingCards.isEmpty()) {
			return Optional.empty();
		}
		final CardInfo card = remainingCards.poll();
		// We add the card to the player
		playerService.addCard(login, card.getSuitEnum(), card.getFaceEnum());
		return Optional.ofNullable(card);
	}

	@Override
	public List<PlayerResultDTO> getUsersByScoreDesc(final Long gameId) throws FunctionalException {
		final List<PlayerResultDTO> result = new ArrayList<>();
		final Optional<Game> game = gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new FunctionalException("the game gameId {}  is not found", gameId);
		}
		game.get().getPlayers().forEach(player -> {
			final PlayerResultDTO userDTO = new PlayerResultDTO();
			userDTO.setId(player.getId());
			userDTO.setLogin(player.getLogin());
			userDTO.setScore(player.getCards().stream().mapToInt(c -> c.getFace().getPoint()).sum());
			result.add(userDTO);
		});
		result.sort(new Comparator<PlayerResultDTO>() {
			@Override
			public int compare(final PlayerResultDTO o1, final PlayerResultDTO o2) {
				return o2.getScore() - o1.getScore();
			}
		});
		return result;
	}

	@Override
	public Optional<SuitCountDTO> getSuitCount(final Long gameId) throws FunctionalException {
		final Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		final SuitCountDTO siCountDTO = new SuitCountDTO();
		game.getCards().forEach(c -> siCountDTO.increment(c.getSuitEnum()));
		return Optional.of(siCountDTO);
	}

}
