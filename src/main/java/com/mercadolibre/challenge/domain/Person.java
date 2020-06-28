package com.mercadolibre.challenge.domain;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The Person table definition<br>
 * Note that this table does not accept repeated entities since its two attributes are keys to the table
 */
@ToString
@EqualsAndHashCode(of = { "type", "dna" })
@SuppressWarnings("serial")
@Table("person")
public class Person implements Serializable {

	@PrimaryKeyColumn(name = "person_type", type = PrimaryKeyType.PARTITIONED)
	private String type;

	@PrimaryKeyColumn(name = "person_dna", type = PrimaryKeyType.CLUSTERED)
	private String dna;

	public Person() {
		// Do nothing
	}

	public Person(final String type, final String dna) {
		super();
		this.type = type;
		this.dna = dna;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

}
