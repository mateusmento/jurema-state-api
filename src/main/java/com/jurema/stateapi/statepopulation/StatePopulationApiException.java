package com.jurema.stateapi.statepopulation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
public class StatePopulationApiException extends RuntimeException {
}
