package com.example.modular.modules.configuration;

import com.example.modular.modules.configuration.states.FlowState;

public class StatesFlowConfiguration {

	FlowState initialState;

	public FlowState getInitialState() {
		return initialState;
	}

	public void setInitialState(FlowState initialState) {
		this.initialState = initialState;
	}
	
}
