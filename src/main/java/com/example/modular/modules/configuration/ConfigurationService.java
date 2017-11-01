package com.example.modular.modules.configuration;

import com.example.modular.modules.configuration.states.FlowState;

public interface ConfigurationService {
	<T> ModuleConfiguration<T> getConfigurationById(String id);
	
	<T> FlowState<T> getStateById(String id);
}
