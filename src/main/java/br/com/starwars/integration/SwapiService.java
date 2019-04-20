package br.com.starwars.integration;

import br.com.starwars.component.UrlBuilder;
import br.com.starwars.dto.PlanetSwapiDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static br.com.starwars.configuration.CacheConfiguration.CACHE_PLANETS;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpHeaders.USER_AGENT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SwapiService {

	private UrlBuilder urlBuilder;
	
	@Cacheable(CACHE_PLANETS)
	public PlanetSwapiDTO getPlanet(String name) {

		RestTemplate restTemplate = new RestTemplate();
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON));
		headers.put(USER_AGENT, singletonList("whatever"));
        ResponseEntity<PlanetSwapiDTO> response = restTemplate.exchange(urlBuilder.swapiUrlPlanets(name), GET, new HttpEntity<String>(headers), PlanetSwapiDTO.class);

        return response.getBody();
	}
}