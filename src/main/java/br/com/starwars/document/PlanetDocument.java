package br.com.starwars.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

import static br.com.starwars.component.MessageBuilder.*;

@Document
@Data
public class PlanetDocument {

	@Id
	private String id;

	@Indexed(unique=true)
	@NotEmpty(message = PLANET_NAME_EMPTY)
	private String name;

	@NotEmpty(message = PLANET_CLIMATE_EMPTY)
	private String climate;

	@NotEmpty(message = PLANET_TERRAIN_EMPTY)
	private String terrain;

	@Transient
	private Integer apparitionsCount;
}