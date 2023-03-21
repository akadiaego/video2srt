//package com.prc.transfer.config;
//
//import com.mongodb.client.MongoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import com.mongodb.client.MongoClients;
//
//@Configuration
//class MongoDBConfig {
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create("mongodb://localhost:27017");
//    }
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), "videoSubtitle");
//    }
//}
