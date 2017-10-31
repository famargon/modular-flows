package com.example.modular.modules;

public interface ModuleResponse <T> {

	ModuleResult getResult();
	
	T getResponse();
	
	static <T> ModuleResponse<T> ok(T response){
		return new ModuleResponseImpl<T>().setResponse(response);
	}
	
	static <T> ModuleResponse<T> error(T response){
		return new ModuleResponseImpl<T>().setResult(ModuleResult.ERROR).setResponse(response);
	}
	
	static class ModuleResponseImpl <T> implements ModuleResponse<T>{
		private ModuleResult result;
		private T response;
		public ModuleResponseImpl(){
			this.result = ModuleResult.OK;
		}
		public ModuleResult getResult() {
			return result;
		}
		public ModuleResponseImpl<T> setResult(ModuleResult result) {
			this.result = result;
			return this;
		}
		public T getResponse() {
			return response;
		}
		public ModuleResponseImpl<T> setResponse(T response) {
			this.response = response;
			return this;
		}
	}
}
