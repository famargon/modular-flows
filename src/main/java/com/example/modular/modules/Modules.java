package com.example.modular.modules;

import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.impl.LogicalModuleConfigurationImpl;
import com.example.modular.modules.configuration.impl.TransformationModuleConfigurationImpl;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.modules.impl.JSModule;
import com.example.modular.repository.datamodel.DTOModuleConfiguration;

public class Modules {
	
	public static <T> ModuleResponse<T> executeModule(ModuleConfiguration<T> config, Message message){
		switch (config.getModuleType()) {
			case JS:
				return new JSModule<T>().apply(config, message);
			default:
				return null;
		}
	}
	
	public static ModuleConfiguration configuration(DTOModuleConfiguration moduleDTO){
		switch (ModuleKind.fromString(moduleDTO.getKind())) {
			case LOGICAL:
				return new LogicalModuleConfigurationImpl(ModuleType.fromString(moduleDTO.getType()),  moduleDTO.getScriptId());
			case TRANSFORMATION:
			default:
				return new TransformationModuleConfigurationImpl(ModuleType.fromString(moduleDTO.getType()),  moduleDTO.getScriptId());
		}
	}
	
}
