package com.mercadolibre.challenge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PersonTest {

	@Test
	public void testEqualsAndHashCode() throws Exception {
		String human = PersonType.HUMAN.name();
		String mutant = PersonType.MUTANT.name();

		String dna1 = "ABCDEF";
		String dna2 = "BCDEFG";

		Person person1 = new Person(human, dna1);
		Person person1Clon = new Person(human, dna1);

		Person person2 = new Person(mutant, dna2);

		// Equals
		assertThat(person1.equals(person1Clon)).isTrue();
		assertThat(person1Clon.equals(person1)).isTrue();

		assertThat(person1.equals(person2)).isFalse();
		assertThat(person1Clon.equals(person2)).isFalse();

		// HashCode
		assertThat(person1.hashCode()).isEqualTo(person1Clon.hashCode());
		assertThat(person1Clon.hashCode()).isEqualTo(person1.hashCode());

		assertThat(person1.hashCode()).isNotEqualTo(person2.hashCode());
		assertThat(person1Clon.hashCode()).isNotEqualTo(person2.hashCode());
	}
}
