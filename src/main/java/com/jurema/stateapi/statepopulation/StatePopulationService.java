package com.jurema.stateapi.statepopulation;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatePopulationService {

	private static final Logger logger = LogManager.getLogger(StatePopulationService.class);

	@Autowired
	private StatePopulationWebClient statePopulationWebClient;

	public List<StatePopulation> list() {
		try {
			var response = statePopulationWebClient.list().execute();
			if (!response.isSuccessful()) {
				logger.error("Failure on state population api {}", response.errorBody().string());
				throw new StatePopulationApiException();
			}
			return response.body();
		} catch(IOException ex) {
			throw new StatePopulationApiException();
		}
	}
}
