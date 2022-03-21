package com.jurema.stateapi.state;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jurema.stateapi.statepopulation.StatePopulation;
import com.jurema.stateapi.statepopulation.StatePopulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;

	@Autowired
	private StatePopulationService statePopulationService;

	public List<StateDto> listStateDto() {
		var population = mapPopulationByUf();
		var states = repo.findAll();

		var stateDtos = states.stream()
			.map(state -> stateWithPopulation(state, population))
			.collect(Collectors.toList());

		return stateDtos;
	}

	Map<String, StatePopulation> mapPopulationByUf() {
		return statePopulationService.list().stream()
			.collect(Collectors.toMap(StatePopulation::getUf, x -> x));
	}

	public StateDto stateWithPopulation(State state, Map<String, StatePopulation> populationByUfMap) {
		var population = populationByUfMap.getOrDefault(state.getUf(), new StatePopulation());
		return new StateDto(state, population);
	}
}
