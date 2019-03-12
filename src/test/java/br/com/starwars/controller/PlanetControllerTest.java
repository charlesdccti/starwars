package br.com.starwars.controller;

import static br.com.starwars.components.UrlBuilder.REQUEST_PATH_NAME;
import static br.com.starwars.components.UrlBuilder.REQUEST_PATH_PLANETS;
import static br.com.starwars.support.ScenarioBuilder.generatePlanet;
import static br.com.starwars.support.ScenarioBuilder.generatePlanetFile;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import br.com.starwars.business.PlanetBusiness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.starwars.document.PlanetDocument;
import br.com.starwars.support.ScenarioBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlanetBusiness business;
    
    @Test
    public void methodCreateShouldReturnStatus201() throws Exception{
        
    	PlanetDocument planet = generatePlanet();
    	when(business.create(any())).thenReturn(planet);

    	mockMvc.perform(post(REQUEST_PATH_PLANETS)
                .content(generatePlanetFile())
                .contentType(APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value(planet.getName()))
            .andExpect(jsonPath("$.climate").value(planet.getClimate()))
			.andExpect(jsonPath("$.terrain").value(planet.getTerrain()));
    }
    
    @Test
    public void methodCreateShouldReturnStatus400BecauseTheBodyWillNotBeSend() throws Exception{
        
        mockMvc.perform(post(REQUEST_PATH_PLANETS)
                .contentType(APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void methodFindAllShouldReturnStatus200() throws Exception{
        
    	List<PlanetDocument> planets = singletonList(generatePlanet());
    	when(business.findAll()).thenReturn(planets);
    	
        mockMvc.perform(get(REQUEST_PATH_PLANETS))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(planets.get(0).getName()))
            .andExpect(jsonPath("$[0].climate").value(planets.get(0).getClimate()))
			.andExpect(jsonPath("$[0].terrain").value(planets.get(0).getTerrain()));
    }
    
    @Test
    public void methodFindByNameShouldReturnStatus200() throws Exception{
        
    	PlanetDocument planet = generatePlanet();
    	when(business.findByName(anyString())).thenReturn(planet);
    	
        mockMvc.perform(get(REQUEST_PATH_PLANETS + REQUEST_PATH_NAME + "/Tatooine"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(planet.getName()))
            .andExpect(jsonPath("$.climate").value(planet.getClimate()))
			.andExpect(jsonPath("$.terrain").value(planet.getTerrain()));
    }
    
    @Test
    public void methodFindByIdShouldReturnStatus200() throws Exception{
        
    	PlanetDocument planet = generatePlanet();
    	when(business.findById(anyString())).thenReturn(planet);
    	
        mockMvc.perform(get(REQUEST_PATH_PLANETS + "/123"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(planet.getName()))
            .andExpect(jsonPath("$.climate").value(planet.getClimate()))
			.andExpect(jsonPath("$.terrain").value(planet.getTerrain()));
    }
    
    @Test
    public void methodDeleteByNameShouldReturnStatus204() throws Exception{
        
        mockMvc.perform(delete(REQUEST_PATH_PLANETS + REQUEST_PATH_NAME + "/Tatooine"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void methodDeleteByIdShouldReturnStatus204() throws Exception{
        
        mockMvc.perform(delete(REQUEST_PATH_PLANETS + "/123"))
        	.andExpect(status().isNoContent());
    }
}