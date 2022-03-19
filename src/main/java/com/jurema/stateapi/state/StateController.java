package com.jurema.stateapi.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateRepository repo;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<State> list() {
		return repo.findAll();
	}
}
