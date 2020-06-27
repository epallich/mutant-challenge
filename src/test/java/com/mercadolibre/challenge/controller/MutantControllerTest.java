package com.mercadolibre.challenge.controller;

import static com.mercadolibre.challenge.test.utilities.MvcControllerTestsUtils.getHuman;
import static com.mercadolibre.challenge.test.utilities.MvcControllerTestsUtils.getMutant;
import static com.mercadolibre.challenge.test.utilities.MvcControllerTestsUtils.porformGetStats;
import static com.mercadolibre.challenge.test.utilities.MvcControllerTestsUtils.porformPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.mercadolibre.challenge.domain.Person;
import com.mercadolibre.challenge.domain.PersonType;
import com.mercadolibre.challenge.dto.MutantDto;
import com.mercadolibre.challenge.dto.StatsDto;
import com.mercadolibre.challenge.repository.PersonRepository;
import com.mercadolibre.challenge.test.utilities.AbstractCassandraSpringBootTest;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class MutantControllerTest extends AbstractCassandraSpringBootTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private JacksonTester<MutantDto> jsonMutantDto;
	@Autowired
	private JacksonTester<StatsDto> jsonStatsDto;

	@Test
	public void validRequestMutant() throws Exception {
		porformPost(mvc, jsonMutantDto.write(getMutant()), status().isOk());
	}

	@Test
	public void validRequestNotMutan() throws Exception {
		porformPost(mvc, jsonMutantDto.write(getHuman()), status().isForbidden());
	}

	@Test
	public void getStatsNormalOnlyTest() throws Exception {

		performGetStatsAndAssert(0L, 0L, new BigDecimal("0"));

		insertPerson(PersonType.NORMAL, "HH1");
		insertPerson(PersonType.NORMAL, "HH2");
		insertPerson(PersonType.NORMAL, "HH3");

		performGetStatsAndAssert(0L, 3L, new BigDecimal("0"));
	}

	@Test
	public void getStatsMutanOnlyTest() throws Exception {

		performGetStatsAndAssert(0L, 0L, new BigDecimal("0"));

		insertPerson(PersonType.MUTANT, "MM1");
		insertPerson(PersonType.MUTANT, "MM2");
		insertPerson(PersonType.MUTANT, "MM3");

		performGetStatsAndAssert(3L, 3L, new BigDecimal("1"));
	}

	@Test
	public void getStatsMixedTest() throws Exception {

		performGetStatsAndAssert(0L, 0L, new BigDecimal("0"));

		insertPerson(PersonType.MUTANT, "MM1");
		insertPerson(PersonType.MUTANT, "MM2");
		insertPerson(PersonType.NORMAL, "HH1");
		insertPerson(PersonType.NORMAL, "HH2");
		insertPerson(PersonType.NORMAL, "HH3");

		performGetStatsAndAssert(2L, 5L, new BigDecimal("0.4"));
	}

	@Test
	public void singleRecordByPersonTest() throws Exception {

		performGetStatsAndAssert(0L, 0L, new BigDecimal("0"));

		String sameDna = "MMMM";
		insertPerson(PersonType.MUTANT, sameDna);
		insertPerson(PersonType.MUTANT, sameDna);

		performGetStatsAndAssert(1L, 1L, new BigDecimal("1"));
	}

	// --- Private methods

	private void performGetStatsAndAssert(Long mutantCount, Long humanCount, BigDecimal ratio) throws Exception {
		MockHttpServletResponse porformGetStats = porformGetStats(mvc, status().isOk());

		ObjectContent<StatsDto> objectContent = jsonStatsDto.parse(porformGetStats.getContentAsString());

		StatsDto statsDto = objectContent.getObject();

		assertThat(statsDto.getMutantCount()).isNotNull().isEqualTo(mutantCount);
		assertThat(statsDto.getHumanCount()).isNotNull().isEqualTo(humanCount);
		assertThat(statsDto.getRatio()).isNotNull().isEqualByComparingTo(ratio);
	}

	private void insertPerson(PersonType type, String dna) {
		personRepository.insert(new Person(type.name(), dna));
	}
}
