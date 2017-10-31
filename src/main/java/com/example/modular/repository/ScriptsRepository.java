package com.example.modular.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.modular.repository.datamodel.Script;

@RepositoryRestResource(path="scripts")
public interface ScriptsRepository extends MongoRepository<Script, String>{

}
