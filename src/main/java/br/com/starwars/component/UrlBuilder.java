package br.com.starwars.component;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlBuilder {

	public static final String REQUEST_PATH_PLANETS = "/planet";
	public static final String REQUEST_PATH_NAME = "/name";
	public static final String PARAM_ID = "/{id}";
	private static final String PARAM_SEARCH = "search";
	
	@Value("${swapi.url.planets}")
	private String url;
	
	public URI swapiUrlPlanets(String name) {
		return fromHttpUrl(url).queryParam(PARAM_SEARCH, name).build().toUri();
	}
}
