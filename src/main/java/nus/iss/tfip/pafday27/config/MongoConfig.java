package nus.iss.tfip.pafday27.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import nus.iss.tfip.pafday27.Constants;

@Configuration
public class MongoConfig implements Constants {

    public static final String DATABASE_BGG = "bgg";
    public static final String DATABASE_SHOWS = "shows";

    // get mongo URL from app.properties
    @Value("${mongo.url}")
    private String mongoUrl;

    // initialise Mongo template
    // use qualifier name to differentiate between different DBs
    @Primary
    @Bean(name=DB_BGG)
    public MongoTemplate createMongoTemplate() {
        // create client
        MongoClient client = MongoClients.create(mongoUrl);
        // return template with client and database (must be correct)
        return new MongoTemplate(client, DATABASE_BGG);
    }

    @Bean(name=DB_NETFLIX)
    public MongoTemplate createShowTemplate() {
        MongoClient client = MongoClients.create(mongoUrl);
        // return template with client and database (must be correct)
        return new MongoTemplate(client, DATABASE_SHOWS);
    }
}

