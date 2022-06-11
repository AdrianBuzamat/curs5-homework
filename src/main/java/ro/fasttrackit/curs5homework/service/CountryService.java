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
        this.countries = Optional.ofNullable(countryProvider.readCountries())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
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

    public List<Country> getCountriesByContinent(String name){
        return countries.stream()
                .filter(country -> country.continent().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Optional<List<String>> getNeighboursById(int id){
        return countries.stream()
                .filter(country -> country.id()==id)
                .map(Country::neighbours)
                .findFirst();
    }

    public List<Country> getCountriesFromContinentByPopulation(String continent, long minPopulation) {
        return countries.stream()
                .filter(p -> p.continent().equalsIgnoreCase(continent))
                .filter(p -> p.population() > minPopulation)
                .collect(Collectors.toList());
    }

    public List<Country> getNeighboursBySelection(String includedNeighbour, String excludedNeighbour) {
        return countries.stream()
                .filter(p -> p.neighbours().contains(includedNeighbour))
                .filter(p -> !p.neighbours().contains(excludedNeighbour))
                .collect(Collectors.toList());
    }
}
