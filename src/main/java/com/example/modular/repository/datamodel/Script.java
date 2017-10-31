package com.example.modular.repository.datamodel;

import org.springframework.data.annotation.Id;

public class Script {

	@Id
	private String id;
	private byte[] content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
}
