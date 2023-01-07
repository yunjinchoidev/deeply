//package com.server.deeply.util.elasticsearch;
//
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
//@Configuration
//public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//        final ClientConfiguration cofiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//        return RestClients.create(cofiguration).rest();
//    }
//}
