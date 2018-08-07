package br.com.starwars.controller;

import static br.com.starwars.components.UrlBuilder.PARAM_ID;
import static br.com.starwars.components.UrlBuilder.PARAM_NAME;
import static br.com.starwars.components.UrlBuilder.REQUEST_PATH_NAME;
import static br.com.starwars.components.UrlBuilder.REQUEST_PATH_PLANETS;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.starwars.business.PlanetBusiness;
import br.com.starwars.model.Planet;

@RestController
@RequestMapping(REQUEST_PATH_PLANETS)
public class PlanetController {

	private static final Logger LOGGER = getLogger(PlanetController.class);
	
	@Autowired
	private PlanetBusiness business;
	
	@PostMapping
	public ResponseEntity<Planet> create(@RequestBody Planet body){
		
		LOGGER.info("Accessing endpoint with method POST with the following parameters: {}", body);
		
		Planet planet = business.create(body);
		
		return ok().body(planet);
	}
	
	@GetMapping
	public ResponseEntity<List<Planet>> findAll(){
		
		LOGGER.info("Accessing endpoint with method GET without parameters.");
		
		List<Planet> planets = business.findAll();
		
		return ok().body(planets);
	}
	
	@GetMapping(REQUEST_PATH_NAME + PARAM_NAME)
	public ResponseEntity<Planet> findByName(@PathVariable String name){
		
		LOGGER.info("Accessing endpoint with method GET with following parameter: {}", name);
		
		Planet planet = business.findByName(name);
		
		return ok().body(planet);
	}
	
	@GetMapping(PARAM_ID)
	public ResponseEntity<Planet> findById(@PathVariable Long id){
		
		LOGGER.info("Accessing endpoint with method GET with following parameter: {}", id);
		
		Planet planet = business.findById(id);
		
		return ok().body(planet);
	}
	
	@DeleteMapping(REQUEST_PATH_NAME + PARAM_NAME)
	public ResponseEntity<Planet> deleteByName(@PathVariable String name){
		
		LOGGER.info("Accessing endpoint with method DELETE with following parameter: {}", name);
		
		business.deleteByName(name);
		
		return noContent().build();
	}
	
	@DeleteMapping(PARAM_ID)
	public ResponseEntity<Planet> deleteById(@PathVariable Long id){
		
		LOGGER.info("Accessing endpoint with method DELETE with following parameter: {}", id);
		
		business.deleteById(id);
		
		return noContent().build();
	}
}
