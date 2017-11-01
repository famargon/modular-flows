package com.example.modular.modules.configuration.states.impl;

import com.example.modular.modules.configuration.LogicalModuleConfiguration;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.states.ConditionedFlowState;
import com.example.modular.modules.configuration.states.FlowState;

public class ConditionedFlowStateImpl extends FlowStateImpl implements ConditionedFlowState{

	private FlowState ifTrueState;
	private FlowState ifFalseState;
	private LogicalModuleConfiguration conditionOfChoice;
	
	public ConditionedFlowStateImpl(FlowState previous, ModuleConfiguration<?> configuration) {
		super(previous, configuration);
	}

	public void setIfTrueState(FlowState ifTrueState) {
		this.ifTrueState = ifTrueState;
	}

	public void setIfFalseState(FlowState ifFalseState) {
		this.ifFalseState = ifFalseState;
	}

	@Override
	public FlowState ifTrue() {
		return ifTrueState;
	}

	@Override
	public FlowState ifFlase() {
		return ifFalseState;
	}

	@Override
	public LogicalModuleConfiguration getConditionOfChoice() {
		return conditionOfChoice;
	}

}
