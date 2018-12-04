package com.logme.card.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "suit", "face" }) })
public class Card {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private SuitEnum suit;

	@Enumerated(EnumType.STRING)
	private FaceEnum face;

	@ManyToMany(mappedBy = "cards")
	private Set<Deck> decks;

	public Card() {
		super();
	}

	public Card(final SuitEnum suit, final FaceEnum face) {
		super();
		this.suit = suit;
		this.face = face;
	}

	/**
	 * @return the suit
	 */
	public SuitEnum getSuit() {
		return suit;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(final SuitEnum suit) {
		this.suit = suit;
	}

	/**
	 * @return the face
	 */
	public FaceEnum getFace() {
		return face;
	}

	/**
	 * @param face the face to set
	 */
	public void setFace(final FaceEnum face) {
		this.face = face;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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

}
