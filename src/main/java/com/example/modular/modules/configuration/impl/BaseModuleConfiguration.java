package com.example.modular.modules.configuration.impl;

import com.example.modular.FlowsApplicationContext;
import com.example.modular.modules.ModuleType;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.repository.ScriptsRepository;
import com.example.modular.repository.datamodel.Script;

public class BaseModuleConfiguration <T> implements ModuleConfiguration<T>{

	protected ModuleType type;
	protected String scriptId;
	protected byte[] script;
	
	public BaseModuleConfiguration(ModuleType type, String scriptId) {
		this.type = type;
		this.scriptId = scriptId;
	}

	public void setScript(byte[] script) {
		this.script = script;
	}

	@Override
	public ModuleType getModuleType() {
		return type;
	}

	@Override
	public String getScriptId() {
		return this.scriptId;
	}

	@Override
	public byte[] getScript() {
		if(getScriptId()!=null && script==null){
			ScriptsRepository scriptsRepo = FlowsApplicationContext.getBean(ScriptsRepository.class);
			Script scriptDTO = scriptsRepo.findOne(getScriptId());
			script = scriptDTO.getContent();
		}
		return script;
	}
	
	
}
