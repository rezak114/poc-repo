package com.logme.card.dto;

import java.util.HashMap;
import java.util.Map;

import com.logme.card.enums.SuitEnum;

public class SuitCountDTO {

	private final Map<SuitEnum, Long> nbCards = new HashMap<>();

	/**
	 * @return the nbCards
	 */
	public Map<SuitEnum, Long> getNbCards() {
		return nbCards;
	}

	public void increment(final SuitEnum suit) {
		if (nbCards.get(suit) == null) {
			nbCards.put(suit, 1L);
		} else {
			nbCards.put(suit, nbCards.get(suit) + 1);
		}
	}
}
