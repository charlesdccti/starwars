package br.com.starwars;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class MainApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MainApplication.class).run(args);
	}
}
