package br.com.starwars.controller;

import br.com.starwars.business.PlanetBusiness;
import br.com.starwars.document.PlanetDocument;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.starwars.component.UrlBuilder.PARAM_ID;
import static br.com.starwars.component.UrlBuilder.REQUEST_PATH_PLANETS;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(REQUEST_PATH_PLANETS)
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanetController {

	private PlanetBusiness business;

	@PostMapping
	public ResponseEntity<PlanetDocument> create(@RequestBody PlanetDocument body) {

		log.info("Accessing endpoint with method POST with the following parameters: {}", body);

		PlanetDocument planet = business.create(body);

		return status(CREATED).body(planet);
	}

	@GetMapping(PARAM_ID)
	public ResponseEntity<PlanetDocument> findById(@PathVariable String id) {

		log.info("Accessing endpoint with method GET with following parameter: {}", id);

		PlanetDocument planet = business.findById(id);

		return ok().body(planet);
	}

	@GetMapping
	public ResponseEntity<?> find(@RequestParam(required = false) String name) {

		log.info("Accessing endpoint with method GET with parameter name:{}", name);

		if(isNull(name)) return ok().body(business.findAll());

		return ok().body(business.findByName(name));
	}

	@DeleteMapping(PARAM_ID)
	@ResponseStatus(NO_CONTENT)
	public void deleteById(@PathVariable String id){

		log.info("Accessing endpoint with method DELETE with following parameter: {}", id);

		business.deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(NO_CONTENT)
	public void deleteByName(@RequestParam String name) {

		log.info("Accessing endpoint with method DELETE with following parameter: {}", name);

		business.deleteByName(name);
	}
}