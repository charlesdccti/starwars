package br.com.starwars.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlanetSwapiDTO {

	private List<Result> results;

	@Data
	public static class Result{
		
		private String name;
		private List<String> films;
	}
}
