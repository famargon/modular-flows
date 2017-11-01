package com.example.modular.modules.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.modular.modules.ModuleResponse;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.modules.types.LogicalModule;

public class JSLogicalModule implements LogicalModule{
	private static final Logger LOG = LoggerFactory.getLogger(JSTransformationModule.class);	
	
	@Override
	public ModuleResponse<Boolean> apply(ModuleConfiguration<Boolean> configuration, Message message) {
		try{
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("nashorn");
			engine.put("$message", message);
			Object obj = engine.eval(new String(configuration.getScript()));
			boolean result = BooleanUtils.toBoolean(obj.toString());
			return ModuleResponse.ok(result);
		}catch(Exception e){
			LOG.error("",e);
			return ModuleResponse.error(null);
		}
	}

}
