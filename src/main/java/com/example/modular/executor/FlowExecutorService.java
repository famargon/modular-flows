package com.example.modular.executor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modular.modules.ModuleResponse;
import com.example.modular.modules.ModuleResult;
import com.example.modular.modules.ModuleType;
import com.example.modular.modules.Modules;
import com.example.modular.modules.configuration.BaseModuleConfiguration;
import com.example.modular.modules.configuration.FlowConfiguration;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.repository.FlowsRepository;
import com.example.modular.repository.ModulesRepository;
import com.example.modular.repository.datamodel.Flow;
import com.example.modular.repository.datamodel.Module;
import com.example.modular.repository.datamodel.ModulePosition;

@Service
public class FlowExecutorService {
	
	@Autowired
	private FlowsRepository flowsRepository;
	@Autowired
	private ModulesRepository modulesRepository;
	
	Comparator<ModulePosition> byPosition = new Comparator<ModulePosition>() {
	    public int compare(ModulePosition m1, ModulePosition m2) {
	    	return Integer.compare(m1.getPosition(), m2.getPosition());
	    }
	};
	
	public Message execute(String flowId, Message inputMessage){
		Flow flow = flowsRepository.findOne(flowId);
		FlowConfiguration flowConfig = new FlowConfiguration();
		List<ModuleConfiguration> modules = new ArrayList<>();
		flowConfig.setModulesSequence(modules);
		flow.getModules().stream()
			.sorted((m1, m2) -> Integer.compare(m1.getPosition(), m2.getPosition()))
			.forEach(modulePosition -> {
				Module moduleDTO = modulesRepository.findOne(modulePosition.getModuleId());
				BaseModuleConfiguration moduleConf = new BaseModuleConfiguration(ModuleType.fromString(moduleDTO.getType()), moduleDTO.getScriptId());
				modules.add(moduleConf);
			});
		return execute(flowConfig, inputMessage);
	}
	
	public Message execute(FlowConfiguration configuration, Message inputMessage){
		Message message = inputMessage;
		for(ModuleConfiguration config : configuration.getModulesSequence()){
			ModuleResponse<Message> response = Modules.executeModule(config,message);
			if(response.getResult().equals(ModuleResult.ERROR)){
				throw new RuntimeException("Error on module");
			}
			message = response.getResponse();
		}
		return message;
	}
	
}
