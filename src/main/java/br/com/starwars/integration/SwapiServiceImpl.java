package br.com.starwars.integration;

import static br.com.starwars.configuration.CacheConfiguration.CACHE_PLANETS;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.starwars.components.UrlBuilder;
import br.com.starwars.model.PlanetSwapi;

@Service
public class SwapiServiceImpl implements SwapiService{

	@Autowired
	private UrlBuilder urlBuilder;
	
	@Override
	@Cacheable(CACHE_PLANETS)
	public PlanetSwapi getPlanet(String name) {
		
		RestTemplate restTemplate = new RestTemplate();
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(asList(APPLICATION_JSON));
		headers.add("user-agent", "teste");
        ResponseEntity<PlanetSwapi> response = restTemplate.exchange(urlBuilder.swapiUrlPlanets(name), GET, new HttpEntity<String>(headers), PlanetSwapi.class);

        return response.getBody();
	}
}
