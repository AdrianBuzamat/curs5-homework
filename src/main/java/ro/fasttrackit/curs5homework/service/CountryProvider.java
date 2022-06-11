package ro.fasttrackit.curs5homework.service;

import ro.fasttrackit.curs5homework.model.Country;

import java.util.List;

public interface CountryProvider {
    List<Country> readCountries();
}
