package com.logme.card.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logme.card.entity.Card;
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
	public Optional<Player> addPlayer(Long gameId, String login) throws FunctionalException {
		final Optional<Player> player = this.playerRepository.findByLogin(login);
		if (player.isPresent()) {
			throw new FunctionalException("The player with login ={} already exists", login);
		}
		final Optional<Game> game = this.gameRepository.findById(gameId);
		if (!game.isPresent()) {
			throw new FunctionalException("The game is not found gameID ={} ", gameId);
		}
		final Player playerNew = new Player();
		playerNew.setGame(game.get());
		playerNew.setLogin(login);
		this.playerRepository.save(playerNew);
		return Optional.ofNullable(playerNew);
	}

	@Override
	public boolean removePlayer(Long gameId, Long playerId) throws FunctionalException {
		boolean removed = false;
		final Optional<Game> gameOp = this.gameRepository.findById(gameId);
		if (null == gameId || null == playerId) {
			throw new FunctionalException("Missing parameter");
		}
		if (!gameOp.isPresent()) {
			throw new FunctionalException("The game is not found gameID ={} ", gameId);
		}
		if (null == gameOp.get().getPlayers()) {
			throw new FunctionalException("The game gameID ={}  doesn't containt playerId = {}", gameId);
		}
		final Game game = gameOp.get();
		final Set<Player> players = game.getPlayers().stream().filter(currGame -> gameId.equals(currGame.getId()) == false).collect(Collectors.toSet());
		if (players.size() < game.getPlayers().size()) {
			game.setPlayers(players);
			this.gameRepository.save(game);
			removed = true;
		}
		return removed;
	}

	@Override
	public Optional<Set<Card>> getCards(String login ) {
		if (null == login) {
			Optional.empty();
		}
		final Optional<Player> player = this.playerRepository.findByLogin(login);
		if (!player.isPresent()) {
			Optional.empty();
		}
		return Optional.of(player.get().getCards());
	}

	@Override
	public void removeCard(String login, SuitEnum suitEnum, FaceEnum faceEnum) {
		final Optional<Player> player = this.playerRepository.findByLogin(login);
		if (player.isPresent()) {
			if(null!=player.get().getCards()) {
				player.get().getCards().remove(new Card(suitEnum, faceEnum));
				this.playerRepository.save(player.get());
			}
		}
		
	}

	@Override
	public boolean addCard(String login, SuitEnum suitEnum, FaceEnum faceEnum) {
		final Optional<Player> player = this.playerRepository.findByLogin(login);
		if (player.isPresent()) {
			if(null==player.get().getCards()) {
				player.get().setCards(new HashSet<>());
			}
			player.get().getCards().add(cardRepository.findBySuitAndFace(suitEnum, faceEnum).get());
			this.playerRepository.save(player.get());
			return true;
		}
		return false;
	}

}
