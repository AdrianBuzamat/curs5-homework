package ro.fasttrackit.curs5homework.service;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs5homework.model.Country;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class CountryReaderFromFile implements CountryProvider {
    @Override
    public List<Country> readCountries() {
        try {
            AtomicInteger id = new AtomicInteger(0);
            return Files.lines(Path.of("C:\\projects\\fasttrackit\\fs02\\curs5-homework\\src\\main\\resources\\static\\countries.txt"))
                    .map(line -> linesToCountry(id.incrementAndGet(), line))
                    .collect(toList());
        } catch (IOException e) {
            System.err.println("Could not find file");
            return List.of();
        }
    }

    private Country linesToCountry(int id, String line) {
        var countryParts = line.split("\\|");
        return new Country(id, countryParts[0]
                , countryParts[1]
                , Long.parseLong(countryParts[2])
                , Double.parseDouble(countryParts[3])
                , countryParts[4]
                , countryParts.length>5? parseNeighbours(countryParts[5]): List.of());
    }

    private List<String> parseNeighbours(String countryPart) {
        return Arrays.stream(countryPart.split("~"))
                .collect(Collectors.toList());
    }
}
