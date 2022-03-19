package com.jurema.stateapi.state;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest
@AutoConfigureDataJpa
public class StateWebMvcTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void shouldListState() throws Exception {
		// given
		var request = get("/states");

		// when
		var response = mvc.perform(request);

		// then
		response.andExpectAll(
			status().isOk(),
			jsonPath("$", hasSize(0))
		);
	}
}
