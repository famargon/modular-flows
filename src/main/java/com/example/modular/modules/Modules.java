package com.example.modular.modules;

import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.modules.impl.JSTransformationModule;

public class Modules {
	
	public static <T extends ModuleConfiguration> ModuleResponse<Message> executeModule(T config, Message message){
		switch (config.getModuleType()) {
			case JS:
				return new JSTransformationModule().apply(config, message);
			default:
				return null;
		}
	}
}
