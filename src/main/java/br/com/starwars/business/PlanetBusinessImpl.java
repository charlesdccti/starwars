package br.com.starwars.business;

import static br.com.starwars.components.Message.PLANET_ALREADY_EXISTS;
import static br.com.starwars.components.Message.PLANET_NOT_FOUND;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starwars.exception.PlanetAlreadyExistsException;
import br.com.starwars.exception.PlanetNotFoundException;
import br.com.starwars.model.Planet;
import br.com.starwars.repository.PlanetRepository;

@Service
public class PlanetBusinessImpl implements PlanetBusiness{
	
	@Autowired
	private PlanetRepository repository;
	@Autowired
	private PlanetSwapiBusiness business;
	
	@Override
	public Planet create(Planet planet) {
		Optional<Planet> p = repository.findByName(planet.getName());
		if(p.isPresent()) throw new PlanetAlreadyExistsException(PLANET_ALREADY_EXISTS);
		planet = repository.insert(planet);
		planet.setApparitionsCount(business.getApparitionsCount(planet.getName()));
		return planet;
	}

	@Override
	public List<Planet> findAll() {
		return repository.findAll().stream().map(p -> {p.setApparitionsCount(business.getApparitionsCount(p.getName())); return p;}).collect(toList());
	}

	@Override
	public Planet findByName(String name) {
		return repository.findByName(name).map(p -> {p.setApparitionsCount(business.getApparitionsCount(p.getName())); return p;})
				.orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
	}

	@Override
	public Planet findById(String id) {
		return repository.findById(id).map(p -> {p.setApparitionsCount(business.getApparitionsCount(p.getName())); return p;})
				.orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
	}

	@Override
	public void deleteByName(String name) {
		Planet planet = repository.findByName(name).orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
		repository.deleteByName(planet.getName());
	}

	@Override
	public void deleteById(String id) {
		Planet planet = repository.findById(id).orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
		repository.deleteById(planet.getId());
	}
}
