package com.example.modular.modules;

public enum ModuleKind {

	LOGICAL,
	TRANSFORMATION;
	
	
	public static ModuleKind fromString(String type){
		for(ModuleKind module : ModuleKind.values()){
			if(module.name().equals(type)){
				return module;
			}
		}
		throw new RuntimeException("Invalid module kind");
	}
}
