package com.logme.card.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Deck {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "GAME_ID", nullable = true)
	private Game game;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, })
	@JoinTable(name = "deck_cards", joinColumns = @JoinColumn(name = "deck_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "card_id", nullable = false))
	private Set<Card> cards;

	public Deck() {
		super();
	}

	public Deck(Game game, Set<Card> cards) {
		super();
		this.game = game;
		this.cards = cards;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return this.game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the cards
	 */
	public Set<Card> getCards() {
		return this.cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @param id the id to set
	 */
	public Long getId() {
		return this.id;
	}

}
