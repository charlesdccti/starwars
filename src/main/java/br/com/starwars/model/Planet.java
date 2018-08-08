package br.com.starwars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planet{

	@Id
	private String id;
	@Indexed(unique=true)
	private String name;
	private String climate;
	private String terrain;
	@Transient
	private Integer apparitionsCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public Integer getApparitionsCount() {
		return apparitionsCount;
	}
	public void setApparitionsCount(Integer apparitionsCount) {
		this.apparitionsCount = apparitionsCount;
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", climate=" + climate + " and terrain=" + terrain;
	}
}
