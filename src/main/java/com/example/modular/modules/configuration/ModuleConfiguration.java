package com.example.modular.modules.configuration;

import com.example.modular.modules.ModuleType;
import com.example.modular.modules.datamodel.Message;

public interface ModuleConfiguration <T>{

	ModuleType getModuleType();
	
	String getScriptId();
	
	byte[] getScript();
	
	T mapResult(Message message, Object result);
	
}
