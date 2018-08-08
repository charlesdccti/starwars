package br.com.starwars.components;

import static br.com.starwars.components.Validate.validate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.exception.FieldRequiredException;
import br.com.starwars.model.Planet;
import br.com.starwars.support.ScenarioBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateTest {

	@Autowired
	private ScenarioBuilder builder;
	
	@Test(expected = FieldRequiredException.class)
	public void methodValidateShouldReturnFieldRequiredExceptionByName() throws Exception{
		
		Planet p = builder.generatePlanet();
		p.setName(null);
		validate(p);
	}

	@Test(expected = FieldRequiredException.class)
	public void methodValidateShouldReturnFieldRequiredExceptionByClimate() throws Exception{
		
		Planet p = builder.generatePlanet();
		p.setClimate(null);
		validate(p);
	}
	
	@Test(expected = FieldRequiredException.class)
	public void methodValidateShouldReturnFieldRequiredExceptionByTerrain() throws Exception{
		
		Planet p = builder.generatePlanet();
		p.setTerrain(null);
		validate(p);
	}
}
