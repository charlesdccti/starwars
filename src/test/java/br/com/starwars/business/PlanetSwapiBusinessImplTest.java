package br.com.starwars.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.exception.PlanetSwapiNotFoundException;
import br.com.starwars.integration.SwapiService;
import br.com.starwars.model.PlanetSwapi;
import br.com.starwars.support.ScenarioBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetSwapiBusinessImplTest {

    @Autowired
    private ScenarioBuilder builder;
    @Autowired
    private PlanetSwapiBusiness business;
    @MockBean
    private SwapiService service;
    
    @Test
    public void methodGetShouldReturnAIngeter() throws Exception{
    	
    	PlanetSwapi p = builder.generatePlanetSwapi();
    	when(service.getPlanet(anyString())).thenReturn(p);
    	
    	Integer i = business.getApparitionsCount("Tatooine");
    	
    	assertThat(p.getResults().get(0).getFilms().size() == i);
    }
    
    @Test(expected = PlanetSwapiNotFoundException.class)
    public void methodGetShouldReturnAPlanetSwapiNotFoundExceptionByName() throws Exception{
    	
    	PlanetSwapi p = builder.generatePlanetSwapi();
    	when(service.getPlanet(anyString())).thenReturn(p);
    	
    	Integer i = business.getApparitionsCount("Tatooin");
    }
    
    @Test(expected = PlanetSwapiNotFoundException.class)
    public void methodGetShouldReturnAPlanetSwapiNotFoundException() throws Exception{
    	
    	PlanetSwapi p = builder.generatePlanetSwapiWithResultVoid();
    	when(service.getPlanet(anyString())).thenReturn(p);
    	
    	Integer i = business.getApparitionsCount("Tatooin");
    }
}
