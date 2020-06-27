package com.mercadolibre.challenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mercadolibre.challenge.controller.MutantController;
import com.mercadolibre.challenge.test.utilities.AbstractCassandraSpringBootTest;

class ChallengeApplicationTests extends AbstractCassandraSpringBootTest {

	@Autowired
	private MutantController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
