package ro.fasttrackit.curs5homework.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs5homework.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private List<Country> countries;

    public CountryService(CountryProvider countryProvider) {
        this.countries = new ArrayList<>(countryProvider.readCountries());
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<String> getAllCountriesName() {
        return countries.stream()
                .map(Country::name)
                .collect(Collectors.toList());
    }

    public Optional<String> getCapitalById(int id) {
        return countries.stream()
                .filter(country -> country.id() == id)
                .map(Country::capital)
                .findFirst();
    }

    public Optional<Long> getPopulationById(int id) {
        return countries.stream()
                .filter(country -> country.id() == id)
                .map(Country::population)
                .findFirst();
    }
//
//- get countries in continent : /continents/<continentName>/countries -> returns list of Country objects
//
    public List<Country> getCountriesByContinent(String name){
        return countries.stream()
                .filter(country -> country.continent().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
//- get country neighbours : /countries/<countryId>/neighbours -> returns list of Strings
}
