package com.example.modular.modules.configuration.states.impl;

import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.states.FlowState;

public class FlowStateImpl implements FlowState{

	private FlowState next;
	private FlowState previous;
	private ModuleConfiguration configuration;
	
	public FlowStateImpl(FlowState previous, ModuleConfiguration configuration){
		this.previous = previous;
		this.configuration = configuration;
	}
	
	public void setNext(FlowState next) {
		this.next = next;
	}

	@Override
	public FlowState getNext() {
		return next;
	}

	@Override
	public FlowState getPrevious() {
		return previous;
	}

	@Override
	public ModuleConfiguration getConfiguration() {
		return configuration;
	}

}
