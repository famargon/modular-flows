package com.example.modular.modules.impl;

import java.util.Map.Entry;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.modular.modules.Module;
import com.example.modular.modules.ModuleResponse;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;

public class JSTransformationModule implements Module<Message, ModuleConfiguration>{

	private static final Logger LOG = LoggerFactory.getLogger(JSTransformationModule.class);	
	
	@Override
	public ModuleResponse<Message> apply(ModuleConfiguration configuration, Message message) {
		try{
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("nashorn");
			engine.put("$message", message);
			Bindings obj = (Bindings)engine.eval(new String(configuration.getScript()));
			return ModuleResponse.ok(bindingsToMessage(message,obj));
		}catch(Exception e){
			LOG.error("",e);
			return ModuleResponse.error(null);
		}
	}
	
	private Message bindingsToMessage(Message message, Bindings obj){
		//message.getMetadata().clear();
		for(Entry<String,Object> e : obj.entrySet()){
			message.getMetadata().put(e.getKey(),e.getValue().toString());
		}
		return message;
	}

}
