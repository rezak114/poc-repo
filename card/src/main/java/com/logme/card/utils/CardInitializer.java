package com.logme.card.utils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.logme.card.entity.Card;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;
import com.logme.card.repository.CardRepository;

/**
 * Service used for initializing different type of {@link Card}.
 *
 * @author rezak
 *
 */
@Scope(scopeName="singleton")
@Service
@Transactional
public class CardInitializer {

	private final static Logger LOGGER = LoggerFactory.getLogger(CardInitializer.class);
	
	@Autowired
	private CardRepository cardRepository;

	@PostConstruct
	public synchronized void initialize() {
		for (final SuitEnum suitEnum : SuitEnum.values()) {
			for (final FaceEnum faceEnum : FaceEnum.values()) {
				final Card card = new Card();
				card.setFace(faceEnum);
				card.setSuit(suitEnum);
				LOGGER.debug("save card suit={} face={}", suitEnum, faceEnum.getLabel());
				try {
					this.cardRepository.save(card);
				} catch (final RuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
