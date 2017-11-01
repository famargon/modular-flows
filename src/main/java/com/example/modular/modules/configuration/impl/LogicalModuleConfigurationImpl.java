package com.example.modular.modules.configuration.impl;

import com.example.modular.modules.ModuleType;
import com.example.modular.modules.configuration.LogicalModuleConfiguration;

public class LogicalModuleConfigurationImpl extends BaseModuleConfiguration<Boolean> implements LogicalModuleConfiguration{

	public LogicalModuleConfigurationImpl(ModuleType type, String scriptId) {
		super(type, scriptId);
	}

}
