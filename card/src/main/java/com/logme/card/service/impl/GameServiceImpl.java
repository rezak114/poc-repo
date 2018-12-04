package com.logme.card.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logme.card.dto.CardCountDTO;
import com.logme.card.dto.CardCountResultDTO;
import com.logme.card.dto.PlayerResultDTO;
import com.logme.card.dto.SuitCountDTO;
import com.logme.card.entity.Card;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.exception.FunctionalException;
import com.logme.card.repository.GameRepository;
import com.logme.card.service.GameService;
import com.logme.card.service.PlayerService;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerService playerService;

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
				for (int index = 0; index < cards.size(); index++) {
					final int randomIndex = (int) Math.random() * 100 % (cards.size() - index);
					game.get().getIndexsShuffled().add(randomIndex);
					cards.remove(randomIndex);
				}
			}
		}
	}

	@Override
	public void deleteGame(final Long gameId) {
		gameRepository.deleteById(gameId);
	}

	@Override
	public Optional<CardInfo> dealCard(final Long gameId, final String login) throws FunctionalException {
		Game game = gameRepository.findById(gameId).orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		final List<Integer> indexesShuff = game.getIndexsShuffled();
		if (null == indexesShuff || indexesShuff.isEmpty() && game.getCards().isEmpty()) {
			return Optional.empty();
		} else if (null == indexesShuff || indexesShuff.isEmpty()) {
			shuffle(gameId);
			game = gameRepository.findById(gameId).orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		}
		final Integer index = indexesShuff.get(0);
		indexesShuff.remove(0);

		final int i = 0;
		CardInfo cardFound = null;
		Card cardToFind = null;
		for (final Card card : game.getCards()) {
			if (i == index) {
				cardToFind = card;
				cardFound = new CardInfo(card.getSuit(), card.getFace());
				// We add the card to the player
				playerService.addCard(login, card.getSuit(), card.getFace());
				break;
			}
		}
		if (cardToFind != null) {
			game.getCards().remove(cardToFind);
			gameRepository.save(game);
		}
		return Optional.ofNullable(cardFound);
	}

	@Override
	public List<PlayerResultDTO> getUsersByScoreDesc(final Long gameId) throws FunctionalException {
		final List<PlayerResultDTO> result = new ArrayList<>();
		final Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		game.getPlayers().forEach(player -> {
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
		game.getCards().forEach(c -> siCountDTO.increment(c.getSuit()));
		return Optional.of(siCountDTO);
	}

	@Override
	public Optional<List<CardCountResultDTO>> getCardCountDTO(final Long gameId) throws FunctionalException {
		final Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		final CardCountDTO counter = new CardCountDTO();
		game.getCards().forEach(c -> counter.increment(c));
		return Optional.of(counter.getResultDesc());
	}

}
