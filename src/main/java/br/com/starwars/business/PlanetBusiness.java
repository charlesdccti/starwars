package br.com.starwars.business;

import static br.com.starwars.components.Message.PLANET_ALREADY_EXISTS;
import static br.com.starwars.components.Message.PLANET_NOT_FOUND;
import static br.com.starwars.components.Validate.validate;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import br.com.starwars.exception.PlanetAlreadyExistsException;
import br.com.starwars.exception.PlanetNotFoundException;
import br.com.starwars.document.PlanetDocument;
import br.com.starwars.repository.PlanetRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanetBusiness {
	
	private PlanetRepository repository;
	private PlanetSwapiBusiness business;
	
	public PlanetDocument create(PlanetDocument planet) {
		validate(planet);
		Optional<PlanetDocument> p = repository.findByName(planet.getName());
		if(p.isPresent()) throw new PlanetAlreadyExistsException(PLANET_ALREADY_EXISTS);
		planet.setApparitionsCount(business.getApparitionsCount(planet.getName()));
		planet = repository.insert(planet);
		return planet;
	}

	public List<PlanetDocument> findAll() {
		return repository.findAll().stream().peek(p -> p.setApparitionsCount(business.getApparitionsCount(p.getName()))).collect(toList());
	}

	public PlanetDocument findByName(String name) {
		return repository.findByName(name).map(p -> {p.setApparitionsCount(business.getApparitionsCount(p.getName())); return p;})
				.orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
	}

	public PlanetDocument findById(String id) {
		return repository.findById(id).map(p -> {p.setApparitionsCount(business.getApparitionsCount(p.getName())); return p;})
				.orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
	}

	public void deleteByName(String name) {
		PlanetDocument planet = repository.findByName(name).orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
		repository.deleteByName(planet.getName());
	}

	public void deleteById(String id) {
		PlanetDocument planet = repository.findById(id).orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
		repository.deleteById(planet.getId());
	}
}
