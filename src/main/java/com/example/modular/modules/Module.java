package com.example.modular.modules;

import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;

public interface Module <R,T extends ModuleConfiguration<R>>{

//	Message apply(T configuration, Message message);
	
	ModuleResponse<R> apply(T configuration, Message message);
	
}
