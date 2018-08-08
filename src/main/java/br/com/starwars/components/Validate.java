package br.com.starwars.components;

import static br.com.starwars.components.Message.PLANET_CLIMATE_EMPTY;
import static br.com.starwars.components.Message.PLANET_NAME_EMPTY;
import static br.com.starwars.components.Message.PLANET_TERRAIN_EMPTY;
import static org.apache.logging.log4j.util.Strings.isBlank;

import br.com.starwars.exception.FieldRequiredException;
import br.com.starwars.model.Planet;

public class Validate {

	public static void validate(Planet planet) {
		
		if(isBlank(planet.getName())) throw new FieldRequiredException(PLANET_NAME_EMPTY);
		if(isBlank(planet.getClimate())) throw new FieldRequiredException(PLANET_CLIMATE_EMPTY);
		if(isBlank(planet.getTerrain())) throw new FieldRequiredException(PLANET_TERRAIN_EMPTY);
	}
}
