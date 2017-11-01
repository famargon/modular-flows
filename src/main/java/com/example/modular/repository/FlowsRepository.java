package com.example.modular.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.modular.repository.datamodel.v1.Flow;

@RepositoryRestResource(path="flows")
public interface FlowsRepository extends MongoRepository<Flow, String>{

}
