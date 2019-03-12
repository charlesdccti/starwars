package br.com.starwars.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class PlanetDocument {

	@Id
	private String id;
	@Indexed(unique=true)
	private String name;
	private String climate;
	private String terrain;
	@Transient
	private Integer apparitionsCount;
}