package br.com.starwars.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.starwars.document.PlanetDocument;

@Repository
public interface PlanetRepository extends MongoRepository<PlanetDocument, Long>{
	
	Optional<PlanetDocument> findById(String id);
	Optional<PlanetDocument> findByName(String name);
	void deleteById(String id);
	void deleteByName(String name);
}
