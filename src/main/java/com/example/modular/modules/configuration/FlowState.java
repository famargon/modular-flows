package com.example.modular.modules.configuration;

public interface FlowState {

	FlowState getNext();
	FlowState getPrevious();
	ModuleConfiguration getConfiguration();
	
}
