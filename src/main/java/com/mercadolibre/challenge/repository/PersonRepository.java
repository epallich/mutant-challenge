package com.mercadolibre.challenge.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.challenge.domain.Person;

@Repository
public interface PersonRepository extends CassandraRepository<Person, String> {

	// countByType

	public Long countByType(final String type);

}
