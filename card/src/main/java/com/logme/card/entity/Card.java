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

	public Card(SuitEnum suit, FaceEnum face) {
		super();
		this.suit = suit;
		this.face = face;
	}

	/**
	 * @return the suit
	 */
	public SuitEnum getSuit() {
		return this.suit;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(SuitEnum suit) {
		this.suit = suit;
	}

	/**
	 * @return the face
	 */
	public FaceEnum getFace() {
		return this.face;
	}

	/**
	 * @param face the face to set
	 */
	public void setFace(FaceEnum face) {
		this.face = face;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((face == null) ? 0 : face.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (face != other.face)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

}
