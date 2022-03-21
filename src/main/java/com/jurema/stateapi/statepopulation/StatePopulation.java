package com.jurema.stateapi.statepopulation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StatePopulation {
	private Integer id;
	private String uf;
	@JsonProperty("populacao")
	private Integer population;
}
