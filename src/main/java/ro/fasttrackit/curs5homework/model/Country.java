package ro.fasttrackit.curs5homework.model;

import java.util.List;

public record Country(int id, String name, String capital, Long population, double area, String continent, List<String> neighbours) {
}
