package br.com.starwars.components;

import static br.com.starwars.components.Validate.validate;
import static br.com.starwars.support.ScenarioBuilder.generatePlanet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.exception.FieldRequiredException;
import br.com.starwars.document.PlanetDocument;
import br.com.starwars.support.ScenarioBuilder;

public class ValidateTest {

	@Test(expected = FieldRequiredException.class)
	public void methodValidateShouldReturnFieldRequiredExceptionByName() {
		
		PlanetDocument p = generatePlanet();
		p.setName(null);
		validate(p);
	}

	@Test(expected = FieldRequiredException.class)
	public void methodValidateShouldReturnFieldRequiredExceptionByClimate() {
		
		PlanetDocument p = generatePlanet();
		p.setClimate(null);
		validate(p);
	}
	
	@Test(expected = FieldRequiredException.class)
	public void methodValidateShouldReturnFieldRequiredExceptionByTerrain() {
		
		PlanetDocument p = generatePlanet();
		p.setTerrain(null);
		validate(p);
	}
}