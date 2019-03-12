package br.com.starwars.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.dto.PlanetSwapiDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SwapiServiceIT {

	@Autowired
	private SwapiService service;
	
	@Test
	public void methodGetShouldReturnAPlanetSwapi() {
		
		PlanetSwapiDTO planet = service.getPlanet("Tatooine");
		
		assertThat(planet.getResults()).hasSize(1).extracting(PlanetSwapiDTO.Result::getName).contains("Tatooine");
	}
}