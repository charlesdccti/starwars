package br.com.starwars.business;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.starwars.exception.PlanetAlreadyExistsException;
import br.com.starwars.exception.PlanetNotFoundException;
import br.com.starwars.model.Planet;
import br.com.starwars.repository.PlanetRepository;
import br.com.starwars.support.ScenarioBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetBusinessImplTest {

    @Autowired
    private ScenarioBuilder builder;
    @Autowired
    private PlanetBusiness business;
    @MockBean
    private PlanetRepository repository;
    @MockBean
    private PlanetSwapiBusiness swapiBusiness;
    
    @Test
    public void methodCreateShouldReturnAPlanet() throws Exception{

		Planet planet = builder.generatePlanet();
		when(repository.findByName(anyString())).thenReturn(empty());
		when(repository.insert(planet)).thenReturn(planet);
		when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);
		
		Planet p = business.create(planet);
		
		assertThat(p.getName().equals(planet.getName()));
		assertThat(p.getClimate().equals(planet.getClimate()));
		assertThat(p.getTerrain().equals(planet.getTerrain()));
		assertThat(p.getApparitionsCount().equals(1));
    }
    
    @Test(expected = PlanetAlreadyExistsException.class)
    public void methodCreateShouldReturnAPlanetAlreadyExistsException() throws Exception{

		Planet planet = builder.generatePlanet();
		when(repository.findByName(anyString())).thenReturn(of(planet));
		
		planet = business.create(planet);
    }
    
    @Test
    public void methodFindAllShouldReturnAListOfPlanet() throws Exception{
    	
    	List<Planet> planets = asList(builder.generatePlanet());
    	when(repository.findAll()).thenReturn(planets);
    	when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);
    	
		List<Planet> ps = business.findAll();
		
		assertThat(planets.get(0).getName().equals(ps.get(0).getName()));
		assertThat(planets.get(0).getClimate().equals(ps.get(0).getClimate()));
		assertThat(planets.get(0).getTerrain().equals(ps.get(0).getTerrain()));
		assertThat(planets.get(0).getApparitionsCount().equals(1));
    }
    
    @Test
    public void methodFindByNameShouldReturnAPlanet() throws Exception{
    	
		Planet planet = builder.generatePlanet();
		when(repository.findByName(anyString())).thenReturn(of(planet));
		when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);
		
		Planet p = business.findByName("test");
		
		assertThat(p.getName().equals(planet.getName()));
		assertThat(p.getClimate().equals(planet.getClimate()));
		assertThat(p.getTerrain().equals(planet.getTerrain()));
		assertThat(p.getApparitionsCount().equals(1));
    }
    
    @Test(expected = PlanetNotFoundException.class)
    public void methodFindByNameShouldReturnAPlanetNotFoundException() throws Exception{
    	
		when(repository.findByName(anyString())).thenReturn(empty());
		Planet planet = business.findByName("test");
    }
    
    @Test
    public void methodFindByIdShouldReturnAPlanet() throws Exception{
    	
		Planet planet = builder.generatePlanet();
		when(repository.findById(anyString())).thenReturn(of(planet));
		when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);
		
		Planet p = business.findById("test");
		
		assertThat(p.getName().equals(planet.getName()));
		assertThat(p.getClimate().equals(planet.getClimate()));
		assertThat(p.getTerrain().equals(planet.getTerrain()));
		assertThat(p.getApparitionsCount().equals(1));
    }
    
    @Test(expected = PlanetNotFoundException.class)
    public void methodFindByIdShouldReturnAPlanetNotFoundException() throws Exception{
    	
		when(repository.findById(anyString())).thenReturn(empty());
		Planet planet = business.findById("test");
    }
    
    @Test
    public void methodDeleteByNameShouldReturnSuccess() throws Exception{
    	
    	Planet planet = builder.generatePlanet();
		when(repository.findByName(anyString())).thenReturn(of(planet));

		business.deleteByName("teste");
    }
    
    @Test(expected = PlanetNotFoundException.class)
    public void methodDeleteByNameShouldReturnAPlanetNotFoundException() throws Exception{
    	
		when(repository.findByName(anyString())).thenReturn(empty());
		
		business.deleteByName("teste");
    }
    
    @Test
    public void methodDeleteByIdShouldReturnSuccess() throws Exception{
    	
    	Planet planet = builder.generatePlanet();
		when(repository.findById(anyString())).thenReturn(of(planet));

		business.deleteById("teste");
    }
    
    @Test(expected = PlanetNotFoundException.class)
    public void methodDeleteByIdShouldReturnAPlanetNotFoundException() throws Exception{
    	
		when(repository.findById(anyString())).thenReturn(empty());
		
		business.deleteById("teste");
    }
}
