package br.com.starwars.support;

import static java.util.Arrays.asList;
import static org.springframework.util.StreamUtils.copyToString;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.starwars.model.Planet;
import br.com.starwars.model.PlanetSwapi;

@Component
public class ScenarioBuilder {

    public static final String PLANET_FILE = "planet.json";
    
    public String generatePlanetFile() throws IOException {
        return copyToString(new ClassPathResource(PLANET_FILE).getInputStream(), Charset.forName("UTF-8"));
    }

    public Planet generatePlanet() {
    	Planet p = new Planet();
    	p.setName("Tatooine");
    	p.setClimate("Temperate");
    	p.setTerrain("Rocks");
    	return p;
    }
    
    public PlanetSwapi generatePlanetSwapi() {
    	PlanetSwapi.Result r = new PlanetSwapi.Result();
    	r.setName("Tatooine");
    	r.setFilms(asList("StarWars 1", "StarWars 2"));
    	PlanetSwapi p = new PlanetSwapi();
    	p.setResults(new ArrayList<>());
    	p.getResults().add(r);
    	return p;
    }
    
    public PlanetSwapi generatePlanetSwapiWithResultVoid() {
    	PlanetSwapi p = new PlanetSwapi();
    	p.setResults(new ArrayList<>());
    	return p;
    }
}
