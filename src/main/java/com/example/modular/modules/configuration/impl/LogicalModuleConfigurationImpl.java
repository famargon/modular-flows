package com.example.modular.modules.configuration.impl;

import org.apache.commons.lang3.BooleanUtils;

import com.example.modular.modules.ModuleType;
import com.example.modular.modules.configuration.LogicalModuleConfiguration;
import com.example.modular.modules.datamodel.Message;

public class LogicalModuleConfigurationImpl extends BaseModuleConfiguration<Boolean> implements LogicalModuleConfiguration{

	public LogicalModuleConfigurationImpl(ModuleType type, String scriptId) {
		super(type, scriptId);
	}

	@Override
	public Boolean mapResult(Message message, Object result) {
		return BooleanUtils.toBoolean(result.toString());
	}

}
