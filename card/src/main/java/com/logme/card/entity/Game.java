package com.logme.card.entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "game")
	private Set<Deck> decks;

	@OneToMany(mappedBy = "game")
	private Set<Player> players;

	/**
	 * This is not persisted, contains all remaining cards.
	 */
	@Transient
	private Queue<CardInfo> cards = new LinkedList<>();

	/**
	 * @return the decks
	 */
	public Set<Deck> getDecks() {
		return this.decks;
	}

	/**
	 * @param decks the decks to set
	 */
	public void setDecks(Set<Deck> decks) {
		this.decks = decks;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the players
	 */
	public Set<Player> getPlayers() {
		return this.players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	/**
	 * @return the cards
	 */
	public Queue<CardInfo> getCards() {
		return this.cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(Queue<CardInfo> cards) {
		this.cards = cards;
	}

}
