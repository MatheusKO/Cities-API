package com.github.matheusko.citiesapi.countries.resources;

import com.github.matheusko.citiesapi.countries.entities.Country;
import com.github.matheusko.citiesapi.countries.repositories.CountryRepository;
import com.github.matheusko.citiesapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryResource {

    @Autowired
    private CountryRepository repository;

    @GetMapping
    public Page<Country> getCountries(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") long id) throws ResourceNotFoundException {
        Country country = verifyIfExists(id);
        return ResponseEntity.ok().body(country);
    }

    // instead of this private method we could use:
    // Optional<Country> optional = repository.findById(id);
    // if(optional.isPresent())
    //      return ResponseEntity.ok().body(optional.get());
    // else return ResponseEntity.notFound().build();
    private Country verifyIfExists (long id) throws ResourceNotFoundException {
        Country country = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found for this id: " + id));
        return country;
    }
}
