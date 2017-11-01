package com.example.modular.modules.configuration.states;

import com.example.modular.modules.configuration.LogicalModuleConfiguration;

public interface ConditionedFlowState <T> extends FlowState<T> {

	LogicalModuleConfiguration getConditionOfChoice();
	FlowState<?> ifTrue();
	FlowState<?> ifFlase();
	void setNext(FlowState<?> nextState);
	
}
