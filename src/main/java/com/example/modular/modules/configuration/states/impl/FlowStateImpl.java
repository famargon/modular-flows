package com.example.modular.modules.configuration.states.impl;

import com.example.modular.modules.configuration.ConfigurationService;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.states.FlowState;

public class FlowStateImpl<T> implements FlowState<T> {

	private ConfigurationService configurationService;

	private String idNext;
	private FlowState<?> next;

	private String idPrevious;
	private FlowState<?> previous;

	private String idConfiguration;
	private ModuleConfiguration<T> configuration;

	public FlowStateImpl(ConfigurationService configurationService, String previous, String configuration) {
		this.configurationService = configurationService;
		this.idPrevious = previous;
		this.idConfiguration = configuration;
	}

	public FlowStateImpl(ConfigurationService configurationService, FlowState<?> previous, ModuleConfiguration<T> configuration) {
		this.configurationService = configurationService;
		this.previous = previous;
		this.configuration = configuration;
	}

	public void setNext(String nextId) {
		this.idNext = nextId;
	}

	public void setPrevious(String idPrevious) {
		this.idPrevious = idPrevious;
	}

	public void setConfiguration(String idConfiguration) {
		this.idConfiguration = idConfiguration;
	}

	public void setPrevious(FlowState<?> previous) {
		this.previous = previous;
	}

	public void setConfiguration(ModuleConfiguration<T> configuration) {
		this.configuration = configuration;
	}

	public void setNext(FlowState<?> next) {
		this.next = next;
	}

	@Override
	public FlowState<?> getNext() {
		if (next == null && idNext != null) {
			this.next = configurationService.getStateById(idNext);
		}
		return next;
	}

	@Override
	public FlowState<?> getPrevious() {
		if (previous == null && idPrevious != null) {
			this.previous = configurationService.getStateById(idPrevious);
		}
		return previous;
	}

	@Override
	public ModuleConfiguration<T> getConfiguration() {
		if (configuration == null && idConfiguration != null) {
			this.configuration = configurationService.getConfigurationById(idConfiguration);
		}
		return configuration;
	}

}
