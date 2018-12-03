package com.logme.card.service;

import java.util.List;
import java.util.Optional;

import com.logme.card.entity.CardInfo;
import com.logme.card.entity.Player;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;
import com.logme.card.exception.FunctionalException;

public interface PlayerService {

	Optional<Player> addPlayer(Long gameId, String login) throws FunctionalException;

	void removePlayer(Long gameId, String login) throws FunctionalException;

	Optional<List<CardInfo>> getCards(String login);

	void removeCard(String login, SuitEnum suitEnum, FaceEnum faceEnum);

	boolean addCard(String login, SuitEnum suitEnum, FaceEnum faceEnum);

}
