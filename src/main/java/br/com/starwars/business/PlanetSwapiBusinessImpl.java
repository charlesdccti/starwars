package br.com.starwars.business;

import static br.com.starwars.components.Message.PLANET_SWAPI_NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starwars.exception.PlanetSwapiNotFoundException;
import br.com.starwars.integration.SwapiService;
import br.com.starwars.model.PlanetSwapi;
import br.com.starwars.model.PlanetSwapi.Result;

@Service
public class PlanetSwapiBusinessImpl implements PlanetSwapiBusiness{

	@Autowired
	private SwapiService service;
	
	@Override
	public Integer getApparitionsCount(String name) {

		PlanetSwapi planet = service.getPlanet(name);
		
		if(planet.getResults().size() > 0) {
			Result result = planet.getResults().stream()
					.filter(r -> r.getName().equalsIgnoreCase(name)).findFirst()
					.orElseThrow(() -> new PlanetSwapiNotFoundException(PLANET_SWAPI_NOT_FOUND));
			return result.getFilms().size();
		}
		
		throw new PlanetSwapiNotFoundException(PLANET_SWAPI_NOT_FOUND);
	}
}
