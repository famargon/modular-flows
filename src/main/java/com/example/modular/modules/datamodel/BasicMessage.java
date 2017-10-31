package com.example.modular.modules.datamodel;

import java.util.HashMap;
import java.util.Map;

public class BasicMessage implements Message{

	private Map<String,String> metadata;

	public BasicMessage(){
		this.metadata = new HashMap<>();
	}
	
	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	
}
