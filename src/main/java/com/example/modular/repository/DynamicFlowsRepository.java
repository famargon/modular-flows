package com.example.modular.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.modular.repository.datamodel.v2.DTOFlowState;

@RepositoryRestResource(path="dynamicflows")
public interface DynamicFlowsRepository extends MongoRepository<DTOFlowState, String>{

}
