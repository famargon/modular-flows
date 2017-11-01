package com.example.modular.repository.datamodel.v2;

import org.springframework.data.annotation.Id;

public class DTOFlowState {

	@Id
	private String id;
	private String idNext;
	private String idPrevious;
	private String idModuleConfiguration;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdNext() {
		return idNext;
	}
	public void setIdNext(String idNext) {
		this.idNext = idNext;
	}
	public String getIdPrevious() {
		return idPrevious;
	}
	public void setIdPrevious(String idPrevious) {
		this.idPrevious = idPrevious;
	}
	public String getIdModuleConfiguration() {
		return idModuleConfiguration;
	}
	public void setIdModuleConfiguration(String idModuleConfiguration) {
		this.idModuleConfiguration = idModuleConfiguration;
	}
	
}
