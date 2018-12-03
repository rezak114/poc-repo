package com.logme.card.repository;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import com.logme.card.CardApplicationTests;
import com.logme.card.entity.Card;
import com.logme.card.enums.FaceEnum;
import com.logme.card.enums.SuitEnum;;

@Rollback(value = true)
public class CardRepositoryTest extends CardApplicationTests {

	@Autowired
	private CardRepository cardRepository;

	@Test
	public void testFindAll() {
		final Iterable<Card> cards = this.cardRepository.findAll();
		Assertions.assertThat(cards).isNotNull();
	}

	@Test
	public void testSave() {
		final Card card = new Card(SuitEnum.CLUB, FaceEnum.ACE);
		this.cardRepository.save(card);
		Assertions.assertThat(card).isNotNull().extracting("id").isNotNull();
	}

	@Test(expected = DataIntegrityViolationException.class)
	@Rollback(false)
	@Ignore
	public void testDuplicateNotSaved() {
		final Card card = new Card(SuitEnum.CLUB, FaceEnum.ACE);
		// first call is ko
		this.cardRepository.save(card);
	}

}
