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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "login" }) })
public class Player {

	@Id
	@GeneratedValue
	private Long id;

	private String login;

	// We consider that a player can play only one game
	@ManyToOne
	@JoinColumn(name = "GAME_ID", nullable = true)
	private Game game;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, })
	@JoinTable(name = "player_cards", joinColumns = @JoinColumn(name = "player_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "card_id", nullable = false))
	private Set<Card> cards;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

}
