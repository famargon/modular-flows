package com.example.modular.modules.configuration.states.impl;

import com.example.modular.modules.configuration.ConfigurationService;
import com.example.modular.modules.configuration.LogicalModuleConfiguration;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.states.ConditionedFlowState;
import com.example.modular.modules.configuration.states.FlowState;

public class ConditionedFlowStateImpl <T> extends FlowStateImpl<T> implements ConditionedFlowState<T>{

	private FlowState<?> ifTrueState;
	private FlowState<?> ifFalseState;
	private LogicalModuleConfiguration conditionOfChoice;
	
	public ConditionedFlowStateImpl(ConfigurationService configurationService, FlowState<?> previous, ModuleConfiguration<T> configuration) {
		super(configurationService, previous, configuration);
	}

	public void setIfTrueState(FlowState<?> ifTrueState) {
		this.ifTrueState = ifTrueState;
	}

	public void setIfFalseState(FlowState<?> ifFalseState) {
		this.ifFalseState = ifFalseState;
	}

	@Override
	public FlowState<?> ifTrue() {
		return ifTrueState;
	}

	@Override
	public FlowState<?> ifFlase() {
		return ifFalseState;
	}

	@Override
	public LogicalModuleConfiguration getConditionOfChoice() {
		return conditionOfChoice;
	}

}
