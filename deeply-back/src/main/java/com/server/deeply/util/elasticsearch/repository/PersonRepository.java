package com.server.deeply.util.elasticsearch.repository;

import com.server.deeply.util.elasticsearch.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}
