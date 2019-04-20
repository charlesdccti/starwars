package br.com.starwars;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StarWarsApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(StarWarsApplication.class).run(args);
	}
}
