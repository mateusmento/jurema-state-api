package com.jurema.stateapi.state;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.jurema.stateapi.statepopulation.StatePopulation;
import com.jurema.stateapi.statepopulation.StatePopulationService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest
@ComponentScan("com.jurema.stateapi.state")
@AutoConfigureDataJpa
public class StateWebMvcTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StatePopulationService statePopulationService;

	@Test
	@Sql(value = "classpath:datasets/states.sql", config = @SqlConfig(encoding = "UTF-8"))
	@Sql(statements = "delete from state", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	void shouldListStateFromDatabase() throws Exception {
		// given
		var request = get("/states");
		when(statePopulationService.list()).thenReturn(statePopulations());

		// when
		var response = mvc.perform(request);

		// then
		response.andExpectAll(
			status().isOk(),
			jsonPath("$", hasSize(2)),
			jsonPath("$[0].uf", equalTo("RJ")),
			jsonPath("$[0].nome", equalTo("Rio de Janeiro")),
			jsonPath("$[0].populacao", equalTo(1000)),
			jsonPath("$[1].uf", equalTo("SP")),
			jsonPath("$[1].nome", equalTo("São Paulo")),
			jsonPath("$[1].populacao", equalTo(2000))
		);
	}

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
}
