package com.example.modular.modules;

public enum ModuleType {

	JS;
	
	public static ModuleType fromString(String type){
		for(ModuleType module : ModuleType.values()){
			if(module.name().equals(type)){
				return module;
			}
		}
		throw new RuntimeException("Invalid module type");
	}
}
