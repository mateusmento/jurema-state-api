package com.jurema.stateapi.statepopulation;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatePopulationConfiguration {

	@Bean
	StatePopulationWebClient StatePopulationService() {
		return new Retrofit.Builder()
			.baseUrl("http://localhost:8081")
			.addConverterFactory(JacksonConverterFactory.create())
			.build()
			.create(StatePopulationWebClient.class);
	}
}
