package com.example.modular.modules.configuration.states;

import com.example.modular.modules.configuration.ModuleConfiguration;

public interface FlowState <T> {

	FlowState<?> getNext();
	FlowState<?> getPrevious();
	ModuleConfiguration<T> getConfiguration();
	
	default boolean isLast(){
		return getNext()==null;
	}
	
	default boolean isFirst(){
		return getPrevious()==null;
	}
}
