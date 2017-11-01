package com.example.modular.modules;

import com.example.modular.modules.configuration.LogicalModuleConfiguration;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.TransformationModuleConfiguration;
import com.example.modular.modules.configuration.impl.BaseModuleConfiguration;
import com.example.modular.modules.configuration.impl.LogicalModuleConfigurationImpl;
import com.example.modular.modules.configuration.impl.TransformationModuleConfigurationImpl;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.modules.impl.JSLogicalModule;
import com.example.modular.modules.impl.JSTransformationModule;

public class Modules {
	
//	public static <T> ModuleResponse<T> executeModule(ModuleConfiguration<T> config, Message message){
//		switch (config.getModuleType()) {
//			case JS:
//				return new JSTransformationModule().apply(config, message);
//			default:
//				return null;
//		}
//	}
	
	public static ModuleResponse<Boolean> executeLogicalModule(LogicalModuleConfiguration config, Message message){
		switch (config.getModuleType()) {
		case JS:
			return new JSLogicalModule().apply(config, message);
		default:
			return null;
		}
	}
	
	public static ModuleResponse<Message> executeTransformationModule(TransformationModuleConfiguration config, Message message){
		switch (config.getModuleType()) {
		case JS:
			return new JSTransformationModule().apply(config, message);
		default:
			return null;
		}
	}
	
	public static ModuleResponse<?> executeModule(ModuleConfiguration<?> config, Message message){
		return LogicalModuleConfiguration.class.isInstance(config) ? 
				executeLogicalModule((LogicalModuleConfiguration) config, message) :
				executeTransformationModule((TransformationModuleConfiguration) config, message);
	}
	
	public static ModuleConfiguration<?> configuration(com.example.modular.repository.datamodel.Module moduleDTO){
		switch (ModuleKind.fromString(moduleDTO.getKind())) {
		case LOGICAL:
			return new LogicalModuleConfigurationImpl(ModuleType.fromString(moduleDTO.getType()),  moduleDTO.getScriptId());
		case TRANSFORMATION:
		default:
			return new TransformationModuleConfigurationImpl(ModuleType.fromString(moduleDTO.getType()),  moduleDTO.getScriptId());
		}
	}
	
}
