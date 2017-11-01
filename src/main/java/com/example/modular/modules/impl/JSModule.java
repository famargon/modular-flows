package com.example.modular.modules.impl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.modular.modules.Module;
import com.example.modular.modules.ModuleResponse;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;

public class JSModule <T> implements Module<T, ModuleConfiguration<T>>{

	private static final Logger LOG = LoggerFactory.getLogger(JSModule.class);	
	
	@Override
	public ModuleResponse<T> apply(ModuleConfiguration<T> configuration, Message message) {
		try{
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("nashorn");
			engine.put("$message", message);
			//TODO set up scope of scriptengine
			Object obj = engine.eval(new String(configuration.getScript()));
			return ModuleResponse.ok(configuration.mapResult(message, obj));
		}catch(Exception e){
			LOG.error("",e);
			return ModuleResponse.error(null);
		}
	}

}
