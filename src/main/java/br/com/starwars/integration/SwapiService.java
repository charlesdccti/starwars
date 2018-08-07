package br.com.starwars.integration;

import br.com.starwars.model.PlanetSwapi;

public interface SwapiService {

	PlanetSwapi getPlanet(String name);
}
