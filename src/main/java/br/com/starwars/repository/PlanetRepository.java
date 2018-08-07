package br.com.starwars.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.starwars.model.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, Long>{
	
	Optional<Planet> findByName(String name);
	void deleteByName(String planetName);
}