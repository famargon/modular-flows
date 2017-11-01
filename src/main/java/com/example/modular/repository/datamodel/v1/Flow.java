package com.example.modular.repository.datamodel.v1;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Flow {

	@Id
	private String id;
	
	private List<ModulePosition> modules;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ModulePosition> getModules() {
		return modules;
	}

	public void setModules(List<ModulePosition> modules) {
		this.modules = modules;
	}
	
}
