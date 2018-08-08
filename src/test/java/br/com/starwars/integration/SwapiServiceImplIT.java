package br.com.starwars.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.model.PlanetSwapi;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SwapiServiceImplIT {

	@Autowired
	private SwapiService service;
	
	@Test
	public void methodGetShouldReturnAPlanetSwapi() {
		
		PlanetSwapi planet = service.getPlanet("Tatooine");
		
		assertThat(planet.getResults()).hasSize(1).extracting(p -> p.getName()).contains("Tatooine");
	}
}
