package com.jurema.stateapi.state;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.List;

import com.jurema.stateapi.statepopulation.StatePopulation;
import com.jurema.stateapi.statepopulation.StatePopulationService;

import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
public class StateListingTests {

	@Autowired
	private StateService service;

	@MockBean
	private StatePopulationService statePopulationService;

	List<StatePopulation> statePopulations() {
		return List.of(
			new StatePopulation() {{
				setId(1);
				setUf("RJ");
				setPopulation(1000);
			}},
			new StatePopulation() {{
				setId(2);
				setUf("SP");
				setPopulation(2000);
			}}
		);
	}

	@Test
	void shouldReturnEmptyStateList() {
		when(statePopulationService.list()).thenReturn(statePopulations());

		var actual = service.listStateDto();

		assertThat(actual, hasSize(0));
	}

	@Test
	@Sql(value = "classpath:datasets/states.sql", config = @SqlConfig(encoding = "UTF-8"))
	@Sql(statements = "delete from state", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void shouldListStateWithPopulation() {
		when(statePopulationService.list()).thenReturn(statePopulations());

		var actual = service.listStateDto();

		assertThat(actual, hasSize(2));

		assertThat(actual, hasItem(allOf(
			hasProperty("uf", equalTo("RJ")),
			hasProperty("name", equalTo("Rio de Janeiro")),
			hasProperty("population", equalTo(1000))
		)));

		assertThat(actual, hasItem(allOf(
			hasProperty("uf", equalTo("SP")),
			hasProperty("name", equalTo("São Paulo")),
			hasProperty("population", equalTo(2000))
		)));
	}
}
