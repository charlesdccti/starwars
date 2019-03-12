package br.com.starwars.controller;

import static br.com.starwars.components.UrlBuilder.PARAM_ID;
import static br.com.starwars.components.UrlBuilder.PARAM_NAME;
import static br.com.starwars.components.UrlBuilder.REQUEST_PATH_NAME;
import static br.com.starwars.components.UrlBuilder.REQUEST_PATH_PLANETS;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import br.com.starwars.business.PlanetBusiness;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.starwars.document.PlanetDocument;

@RestController
@RequestMapping(REQUEST_PATH_PLANETS)
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanetController {

	private PlanetBusiness business;
	
	@PostMapping
	public ResponseEntity<PlanetDocument> create(@RequestBody PlanetDocument body){
		
		log.info("Accessing endpoint with method POST with the following parameters: {}", body);
		
		PlanetDocument planet = business.create(body);
		
		return status(CREATED).body(planet);
	}
	
	@GetMapping
	public ResponseEntity<List<PlanetDocument>> findAll(){

		log.info("Accessing endpoint with method GET without parameters.");
		
		List<PlanetDocument> planets = business.findAll();
		
		return ok().body(planets);
	}
	
	@GetMapping(REQUEST_PATH_NAME + PARAM_NAME)
	public ResponseEntity<PlanetDocument> findByName(@PathVariable String name){

		log.info("Accessing endpoint with method GET with following parameter: {}", name);
		
		PlanetDocument planet = business.findByName(name);
		
		return ok().body(planet);
	}
	
	@GetMapping(PARAM_ID)
	public ResponseEntity<PlanetDocument> findById(@PathVariable String id){

		log.info("Accessing endpoint with method GET with following parameter: {}", id);
		
		PlanetDocument planet = business.findById(id);
		
		return ok().body(planet);
	}
	
	@DeleteMapping(REQUEST_PATH_NAME + PARAM_NAME)
	public ResponseEntity<?> deleteByName(@PathVariable String name){

		log.info("Accessing endpoint with method DELETE with following parameter: {}", name);
		
		business.deleteByName(name);
		
		return noContent().build();
	}
	
	@DeleteMapping(PARAM_ID)
	public ResponseEntity<?> deleteById(@PathVariable String id){

		log.info("Accessing endpoint with method DELETE with following parameter: {}", id);
		
		business.deleteById(id);
		
		return noContent().build();
	}
}