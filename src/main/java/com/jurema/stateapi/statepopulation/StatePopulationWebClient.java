package com.jurema.stateapi.statepopulation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatePopulationWebClient {

	@GET("/state-populations")
	Call<List<StatePopulation>> list();
}
