package com.jurema.stateapi.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jurema.stateapi.statepopulation.StatePopulation;

import lombok.Getter;

@Getter
public class StateDto {

	private String uf;
	@JsonProperty("nome")
	private String name;
	@JsonProperty("populacao")
	private Integer population;

	public StateDto(State state, StatePopulation statePopulation) {
		uf = state.getUf();
		name = state.getName();
		population = statePopulation.getPopulation();
	}
}
