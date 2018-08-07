package br.com.starwars.business;

import static br.com.starwars.components.Message.PLANET_ALREADY_EXISTS;
import static br.com.starwars.components.Message.PLANET_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		
		if(p.isPresent())
			throw new PlanetAlreadyExistsException(PLANET_ALREADY_EXISTS);
		
		planet.setApparitionsCount(business.getApparitionsCount(planet.getName()));
		
		planet = repository.insert(planet);
		
		return planet;
	}

	@Override
	public List<Planet> findAll() {
		return repository.findAll();
	}

	@Override
	public Planet findByName(String name) {
		return repository.findByName(name).orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
	}

	@Override
	public Planet findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new PlanetNotFoundException(PLANET_NOT_FOUND));
	}

	@Override
	public void deleteByName(String name) {
		try {
			repository.deleteByName(name);
		} catch (EmptyResultDataAccessException e) {
			throw new PlanetNotFoundException(PLANET_NOT_FOUND);
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PlanetNotFoundException(PLANET_NOT_FOUND);
		}
	}
}
