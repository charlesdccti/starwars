package br.com.starwars.model;

import java.util.List;
import java.util.Optional;

public class PlanetSwapi {

	private List<Result> results;
	
	public static class Result{
		
		private String name;
		private List<String> films;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<String> getFilms() {
			return films;
		}
		public void setFilms(List<String> films) {
			this.films = films;
		}
	}
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
}
