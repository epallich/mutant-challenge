package com.mercadolibre.challenge.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.challenge.domain.Person;

/**
 * The Person Repository, managing all operations over the Person entity
 */
@Repository
public interface PersonRepository extends CassandraRepository<Person, String> {

	// countByType

	public long countByType(final String type);

}
