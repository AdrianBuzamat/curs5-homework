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

    public List<String> getAllCountriesName(){
        return countries.stream()
                .map(country -> country.name())
                .collect(Collectors.toList());
    }
//
//- get capital of a country : /countries/<countryId>/capital -> returns a string
    public Optional<String> getCapitalById(int id){
        return countries.stream()
                .filter(country -> country.id()==id)
                .map(Country::capital)
                .findFirst();
    }
//
//- get population of a country : /countries/<countryId>/population -> returns a long
//
//- get countries in continent : /continents/<continentName>/countries -> returns list of Country objects
//
//- get country neighbours : /countries/<countryId>/neighbours -> returns list of Strings
}
