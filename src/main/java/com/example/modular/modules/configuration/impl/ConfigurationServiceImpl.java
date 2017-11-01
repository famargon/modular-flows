package com.example.modular.modules.configuration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modular.modules.Modules;
import com.example.modular.modules.configuration.ConfigurationService;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.states.FlowState;
import com.example.modular.modules.configuration.states.impl.FlowStateImpl;
import com.example.modular.repository.DynamicFlowsRepository;
import com.example.modular.repository.ModuleConfigurationsRepository;
import com.example.modular.repository.datamodel.DTOModuleConfiguration;
import com.example.modular.repository.datamodel.v2.DTOFlowState;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private ModuleConfigurationsRepository moduleConfigRepository;
	private DynamicFlowsRepository flowRepository;
	
	@Autowired
	public ConfigurationServiceImpl(ModuleConfigurationsRepository moduleConfigRepository, DynamicFlowsRepository flowRepository) {
		this.moduleConfigRepository = moduleConfigRepository;
		this.flowRepository = flowRepository;
	}
	
	@Override
	public <T> ModuleConfiguration<T> getConfigurationById(String id) {
		DTOModuleConfiguration dto = moduleConfigRepository.findOne(id);
		return Modules.configuration(dto);
	}

	@Override
	public <T> FlowState<T> getStateById(String id) {
		DTOFlowState flow = flowRepository.findOne(id);
		return new FlowStateImpl<>(this, flow.getIdPrevious(), flow.getIdModuleConfiguration());
	}

}
