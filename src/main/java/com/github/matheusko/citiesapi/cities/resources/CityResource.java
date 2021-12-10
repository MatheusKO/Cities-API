package com.github.matheusko.citiesapi.cities.resources;

import com.github.matheusko.citiesapi.cities.entities.City;
import com.github.matheusko.citiesapi.cities.repositories.CityRepository;
import com.github.matheusko.citiesapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityResource {

    @Autowired
    private CityRepository repository;

    @GetMapping()
    public Page<City> getCities(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") long id) {
        try {
            City city = verifyIfExists(id);
            return ResponseEntity.ok().body(city);
        } catch (ResourceNotFoundException e) {
            e.getMessage();
            return ResponseEntity.notFound().build();
        }
    }

    private City verifyIfExists(long id) throws ResourceNotFoundException {
        City city = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id: " + id));
        return city;
    }
}
