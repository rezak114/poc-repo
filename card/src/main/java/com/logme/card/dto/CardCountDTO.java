package com.logme.card.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logme.card.entity.Card;

public class CardCountDTO {

	private final Map<CardCountResultDTO, Integer> nbCards = new HashMap<>();

	/**
	 * @return the nbCards
	 */
	public Map<CardCountResultDTO, Integer> getNbCards() {
		return nbCards;
	}

	public void increment(final Card card) {
		final CardCountResultDTO cardInfo = new CardCountResultDTO(card.getSuit(), card.getFace());
		if (nbCards.get(cardInfo) == null) {
			cardInfo.setNb(card.getFace().getPoint());
			nbCards.put(cardInfo, card.getFace().getPoint());
		} else {
			final int total = nbCards.get(cardInfo) + card.getFace().getPoint();
			cardInfo.setNb(total);
			nbCards.put(cardInfo, total);
		}
	}

	public List<CardCountResultDTO> getResultDesc() {
		final List<CardCountResultDTO> result = new ArrayList(nbCards.keySet());
		result.sort(new Comparator<CardCountResultDTO>() {
			@Override
			public int compare(final CardCountResultDTO o1, final CardCountResultDTO o2) {
				return o2.getNb() - o1.getNb();
			}
		});
		return result;
	}
}
