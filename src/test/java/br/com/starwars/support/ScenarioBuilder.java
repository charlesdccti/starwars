package br.com.starwars.support;

import static java.nio.charset.Charset.forName;
import static java.util.Arrays.asList;
import static org.springframework.util.StreamUtils.copyToString;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.starwars.document.PlanetDocument;
import br.com.starwars.dto.PlanetSwapiDTO;

public class ScenarioBuilder {

    private static final String PLANET_FILE = "planet.json";
    
    public static String generatePlanetFile() throws IOException {
        return copyToString(new ClassPathResource(PLANET_FILE).getInputStream(), forName("UTF-8"));
    }

    public static PlanetDocument generatePlanet() {
    	PlanetDocument p = new PlanetDocument();
    	p.setName("Tatooine");
    	p.setClimate("Temperate");
    	p.setTerrain("Rocks");
    	return p;
    }
    
    public static PlanetSwapiDTO generatePlanetSwapi() {
    	PlanetSwapiDTO.Result r = new PlanetSwapiDTO.Result();
    	r.setName("Tatooine");
    	r.setFilms(asList("StarWars 1", "StarWars 2"));
    	PlanetSwapiDTO p = new PlanetSwapiDTO();
    	p.setResults(new ArrayList<>());
    	p.getResults().add(r);
    	return p;
    }
    
    public static PlanetSwapiDTO generatePlanetSwapiWithResultVoid() {
    	PlanetSwapiDTO p = new PlanetSwapiDTO();
    	p.setResults(new ArrayList<>());
    	return p;
    }
}
