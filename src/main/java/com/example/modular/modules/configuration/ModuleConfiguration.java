package com.example.modular.modules.configuration;

import com.example.modular.modules.ModuleType;

public interface ModuleConfiguration {

	ModuleType getModuleType();
	
	String getScriptId();
	
	byte[] getScript();
	
}
