package br.com.starwars.business;

import static br.com.starwars.support.ScenarioBuilder.generatePlanetSwapi;
import static br.com.starwars.support.ScenarioBuilder.generatePlanetSwapiWithResultVoid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.exception.PlanetSwapiNotFoundException;
import br.com.starwars.integration.SwapiService;
import br.com.starwars.dto.PlanetSwapiDTO;
import br.com.starwars.support.ScenarioBuilder;

@RunWith(MockitoJUnitRunner.class)
public class PlanetSwapiBusinessTest {

    @Mock
    private SwapiService service;
    @InjectMocks
    private PlanetSwapiBusiness business;

    @Test
    public void methodGetShouldReturnAIngeter() {
    	
    	PlanetSwapiDTO p = generatePlanetSwapi();
    	when(service.getPlanet(anyString())).thenReturn(p);
    	
    	Integer i = business.getApparitionsCount("Tatooine");
    	
    	assertThat(p.getResults().get(0).getFilms().size()).isEqualTo(i);
    }
    
    @Test(expected = PlanetSwapiNotFoundException.class)
    public void methodGetShouldReturnAPlanetSwapiNotFoundExceptionByName() {
    	
    	PlanetSwapiDTO p = generatePlanetSwapi();
    	when(service.getPlanet(anyString())).thenReturn(p);
    	
    	business.getApparitionsCount("Tatooin");
    }
    
    @Test(expected = PlanetSwapiNotFoundException.class)
    public void methodGetShouldReturnAPlanetSwapiNotFoundException() {
    	
    	PlanetSwapiDTO p = generatePlanetSwapiWithResultVoid();
    	when(service.getPlanet(anyString())).thenReturn(p);
    	
    	business.getApparitionsCount("Tatooin");
    }
}