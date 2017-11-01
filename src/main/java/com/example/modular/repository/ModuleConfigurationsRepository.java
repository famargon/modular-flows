package com.example.modular.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.modular.repository.datamodel.DTOModuleConfiguration;

@RepositoryRestResource(path="modules")
public interface ModuleConfigurationsRepository extends MongoRepository<DTOModuleConfiguration, String>{

}
