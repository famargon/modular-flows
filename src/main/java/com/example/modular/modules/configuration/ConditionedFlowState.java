package com.example.modular.modules.configuration;

public interface ConditionedFlowState extends FlowState {

	boolean evaluateCondition();
	
}
