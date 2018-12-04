package com.logme.card.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, })
	@JoinTable(name = "game_cards", joinColumns = @JoinColumn(name = "deck_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "card_id", nullable = false))
	private List<Card> cards;

	/**
	 * This is not persisted.
	 */
	@Transient
	private List<Integer> indexsShuffled = new ArrayList<Integer>();

	/**
	 * @return the indexsShuffled
	 */
	public List<Integer> getIndexsShuffled() {
		return indexsShuffled;
	}

	/**
	 * @param indexsShuffled the indexsShuffled to set
	 */
	public void setIndexsShuffled(final List<Integer> indexsShuffled) {
		this.indexsShuffled = indexsShuffled;
	}

	/**
	 * @return the decks
	 */
	public Set<Deck> getDecks() {
		return decks;
	}

	/**
	 * @param decks the decks to set
	 */
	public void setDecks(final Set<Deck> decks) {
		this.decks = decks;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the players
	 */
	public Set<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(final Set<Player> players) {
		this.players = players;
	}

	/**
	 * @return the cards
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(final List<Card> cards) {
		this.cards = cards;
	}

}
