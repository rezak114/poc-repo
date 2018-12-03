package com.logme.card.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.logme.card.CardApplicationTests;
import com.logme.card.repository.CardRepository;

@Rollback(value = true)
public class CardInitializerTest extends CardApplicationTests {

	private static long NB_TOTAL_CARD = 52l;

	@Autowired
	private CardRepository cardRepository;

	@Test
	public void testInitialize() {
		System.out.println("testInitialize############ NB cards" + this.cardRepository.count());
		final long nbCard = this.cardRepository.count();
		Assertions.assertThat(nbCard).isEqualByComparingTo(NB_TOTAL_CARD);
	}

}
