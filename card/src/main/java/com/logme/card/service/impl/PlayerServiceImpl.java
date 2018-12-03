package com.logme.card.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logme.card.entity.Card;
import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Game;
import com.logme.card.entity.Player;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;
import com.logme.card.exception.FunctionalException;
import com.logme.card.repository.CardRepository;
import com.logme.card.repository.GameRepository;
import com.logme.card.repository.PlayerRepository;
import com.logme.card.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private GameRepository gameRepository;

	@Override
	public Optional<Player> addPlayer(final Long gameId, final String login) throws FunctionalException {
		final Optional<Player> player = playerRepository.findByLogin(login);
		if (player.isPresent()) {
			throw new FunctionalException("The player with login ={} already exists", login);
		}
		final Optional<Game> game = gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new FunctionalException("The game is not found gameID ={} ", gameId);
		}
		final Player playerNew = new Player();
		playerNew.setGame(game.get());
		playerNew.setLogin(login);
		playerRepository.save(playerNew);
		return Optional.ofNullable(playerNew);
	}

	@Override
	public void removePlayer(final Long gameId, final String login) throws FunctionalException {
		if (null == gameId || null == login) {
			throw new FunctionalException("Missing parameter");
		}
		final Optional<Game> gameOp = gameRepository.findById(gameId);
		final Game game = gameOp.orElseThrow(() -> new FunctionalException("The game is not found gameID ={} ", gameId));
		if (null == game.getPlayers()) {
			throw new FunctionalException("The game gameID ={}  doesn't containt playerId = {}", gameId);
		}
		final Optional<Player> player = game.getPlayers().stream().filter(p -> p.getLogin().equals(login)).findFirst();

		player.ifPresent(p -> {
			game.getPlayers().remove(player.get());
			player.get().getCards().forEach(c -> {
//				final CardInfo cardInfo = new CardInfo(c.getSuit(), c.getFace());
				game.getCards().add(c);
			});
			gameRepository.save(game);
		});

	}

	@Override
	public Optional<List<CardInfo>> getCards(final String login) {
		if (null == login) {
			Optional.empty();
		}
		final Optional<Player> player = playerRepository.findByLogin(login);
		if (!player.isPresent()) {
			Optional.empty();
		}
		final List<CardInfo> list = new ArrayList<CardInfo>();
		player.get().getCards().forEach(c -> {
			final CardInfo cardInfo = new CardInfo(c.getSuit(), c.getFace());
			list.add(cardInfo);
		});
		return Optional.of(list);
	}

	@Override
	public void removeCard(final String login, final SuitEnum suitEnum, final FaceEnum faceEnum) {
		final Optional<Player> player = playerRepository.findByLogin(login);
		if (player.isPresent()) {
			if (null != player.get().getCards()) {
				player.get().getCards().remove(new Card(suitEnum, faceEnum));
				playerRepository.save(player.get());
			}
		}

	}

	@Override
	public boolean addCard(final String login, final SuitEnum suitEnum, final FaceEnum faceEnum) {
		final Optional<Player> player = playerRepository.findByLogin(login);
		if (player.isPresent()) {
			if (null == player.get().getCards()) {
				player.get().setCards(new HashSet<>());
			}
			player.get().getCards().add(cardRepository.findBySuitAndFace(suitEnum, faceEnum).get());
			playerRepository.save(player.get());
			return true;
		}
		return false;
	}

}
