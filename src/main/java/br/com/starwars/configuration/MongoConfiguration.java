package br.com.starwars.configuration;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongoConfiguration {

    @Value("${mongo.host}") private String host;
    @Value("${mongo.name}") private String name;

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {

        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(host);
        MongoClient mongoClient = mongo.getObject();
        return new MongoTemplate(mongoClient, name);
    }
}
