package com.example.modular.modules.configuration.impl;

import com.example.modular.modules.ModuleType;
import com.example.modular.modules.configuration.TransformationModuleConfiguration;
import com.example.modular.modules.datamodel.Message;

public class TransformationModuleConfigurationImpl extends BaseModuleConfiguration<Message> implements TransformationModuleConfiguration{

	public TransformationModuleConfigurationImpl(ModuleType type, String scriptId) {
		super(type, scriptId);
	}

}
