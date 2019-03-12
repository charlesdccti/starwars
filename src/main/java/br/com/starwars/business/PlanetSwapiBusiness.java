package br.com.starwars.business;

import static br.com.starwars.components.Message.PLANET_SWAPI_NOT_FOUND;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starwars.exception.PlanetSwapiNotFoundException;
import br.com.starwars.integration.SwapiService;
import br.com.starwars.dto.PlanetSwapiDTO;
import br.com.starwars.dto.PlanetSwapiDTO.Result;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PlanetSwapiBusiness {

	private SwapiService service;
	
	Integer getApparitionsCount(String name) {

		PlanetSwapiDTO planet = service.getPlanet(name);
		
		if(planet.getResults().size() > 0) {
			Result result = planet.getResults().stream()
					.filter(r -> r.getName().equals(name)).findFirst()
					.orElseThrow(() -> new PlanetSwapiNotFoundException(PLANET_SWAPI_NOT_FOUND));
			return result.getFilms().size();
		}
		
		throw new PlanetSwapiNotFoundException(PLANET_SWAPI_NOT_FOUND);
	}
}
