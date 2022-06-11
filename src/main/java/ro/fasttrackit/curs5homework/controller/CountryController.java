package ro.fasttrackit.curs5homework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.curs5homework.model.Country;
import ro.fasttrackit.curs5homework.service.CountryService;
import ro.fasttrackit.curs5homework.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {
    private CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Country> getAllCountries(){
        return service.getCountries();
    }

    @GetMapping("/names")
    public List<String> getAllCountriesName(){
        return service.getAllCountriesName();
    }

    @GetMapping({"{id}/capital"})
    public String getCapialById(@PathVariable int id){
        return service.getCapitalById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Could not find country with id " + id));
    }

    @GetMapping({"{id}/population"})
    public Long getPopulationById(@PathVariable int id){
        return service.getPopulationById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Could not find country with id " + id));
    }

    @GetMapping("{continentName}/countries")
    public List<Country> getCountriesByContinent(@PathVariable String continentName){
        return service.getCountriesByContinent(continentName);
    }

}
