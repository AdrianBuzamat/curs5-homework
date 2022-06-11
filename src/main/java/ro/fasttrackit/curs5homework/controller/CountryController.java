package ro.fasttrackit.curs5homework.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs5homework.model.Country;
import ro.fasttrackit.curs5homework.service.CountryService;
import ro.fasttrackit.curs5homework.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("countries")
public class CountryController {
    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Country> getAllCountries() {
        return service.getCountries();
    }

    @GetMapping("/names")
    public List<String> getAllCountriesName() {
        return service.getAllCountriesName();
    }

    @GetMapping({"{id}/capital"})
    public String getCapialById(@PathVariable int id) {
        return service.getCapitalById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find country with id " + id));
    }

    @GetMapping({"{id}/population"})
    public Long getPopulationById(@PathVariable int id) {
        return service.getPopulationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find country with id " + id));
    }

    @GetMapping("{continentName}/countries")
    public List<Country> getCountriesByContinent(@PathVariable String continentName) {
        return service.getCountriesByContinent(continentName);
    }

    @GetMapping({"{id}/neighbours"})
    public List<String> getNeighboursById(@PathVariable int id) {
        return service.getNeighboursById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find country with id " + id));
    }

    @GetMapping(value = "{continentName}/countries", params = {"minPopulation"})
    public List<Country> getCountriesFromContinentByPopulation(@PathVariable String continentName, @RequestParam long minPopulation) {
        return service.getCountriesFromContinentByPopulation(continentName, minPopulation);
    }

    @GetMapping(params = {"includeNeighbour", "excludeNeighbour"})
    public List<Country> getCountriesSelectedNeighbours(@RequestParam String includeNeighbour, @RequestParam String excludeNeighbour) {
        return service.getNeighboursBySelection(includeNeighbour, excludeNeighbour);
    }

    @GetMapping("/population")
    public Map<String, Long> mapCountriesToPopulation() {
        return service.mapCountriesToPopulation();
    }

    @GetMapping("/continents/countries")
    public Map<String, List<Country>> mapContinentToCountries() {
        return service.mapContinentToCountries();
    }

}
