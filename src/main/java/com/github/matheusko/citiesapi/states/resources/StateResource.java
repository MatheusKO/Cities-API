package com.github.matheusko.citiesapi.states.resources;

import com.github.matheusko.citiesapi.exceptions.ResourceNotFoundException;
import com.github.matheusko.citiesapi.states.entities.State;
import com.github.matheusko.citiesapi.states.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateResource {

    @Autowired
    private StateRepository repository;

    @GetMapping
    public Page<State> getStates(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getStateById(@PathVariable("id") long id) throws ResourceNotFoundException {
        State state = verifyIfExists(id);
        return ResponseEntity.ok().body(state);
    }

    private State verifyIfExists(long id) throws ResourceNotFoundException {
        State state = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State not found for this id: " + id));
        return state;
    }
}
