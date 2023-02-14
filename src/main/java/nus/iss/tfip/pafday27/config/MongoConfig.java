package nus.iss.tfip.pafday27.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
    
    public static final String DATABASE = "bgg";

    // get mongo URL from app.properties
    @Value("${mongo.url}")
    private String mongoUrl;

    // initialise Mongo template
    // use qualifier name to differentiate between different DBs
    @Bean
    public MongoTemplate createMongoTemplate() {
        // create client
        MongoClient client = MongoClients.create(mongoUrl);
        // return template with client and database (must be correct)
        return new MongoTemplate(client, DATABASE);
    }
}

