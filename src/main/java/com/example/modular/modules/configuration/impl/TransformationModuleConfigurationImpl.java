package com.example.modular.modules.configuration.impl;

import java.util.Map.Entry;

import javax.script.Bindings;

import com.example.modular.modules.ModuleType;
import com.example.modular.modules.configuration.TransformationModuleConfiguration;
import com.example.modular.modules.datamodel.BasicMessage;
import com.example.modular.modules.datamodel.Message;

public class TransformationModuleConfigurationImpl extends BaseModuleConfiguration<Message> implements TransformationModuleConfiguration{

	public TransformationModuleConfigurationImpl(ModuleType type, String scriptId) {
		super(type, scriptId);
	}

	@Override
	public Message mapResult(Message message, Object result) {
		for(Entry<String,Object> e : ((Bindings)result).entrySet()){
			message.getMetadata().put(e.getKey(),e.getValue().toString());
		}
		return message;
	}
	
}
