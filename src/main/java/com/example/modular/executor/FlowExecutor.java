package com.example.modular.executor;


import com.example.modular.modules.ModuleResponse;
import com.example.modular.modules.Modules;
import com.example.modular.modules.configuration.StatesFlowConfiguration;
import com.example.modular.modules.configuration.states.ConditionedFlowState;
import com.example.modular.modules.configuration.states.FlowState;
import com.example.modular.modules.datamodel.Message;

public class FlowExecutor {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Message executeFlow(StatesFlowConfiguration flow, Message inputMessage){
		
		FlowState state = flow.getInitialState();
		Message message = inputMessage;
		ModuleResponse response;
		do{
			if(!state.isFirst()){
				state = state.getNext();	
			}
			
			response = executeState(state,message); 
			
			if(!response.isOk()){
				throw new RuntimeException("Error on module");
			}
			
			if(Message.class.isInstance(response.getResponse())){
				message = (Message) response.getResponse();	
			}
			
		}while(!state.isLast());
		


		return message;
	}
	
	private <T> ModuleResponse<T> executeState(FlowState<T> state, Message message){
		
		ModuleResponse<T> response = Modules.executeModule(state.getConfiguration(), message);
		if(ConditionedFlowState.class.isInstance(state)){
			ConditionedFlowState<T> conditioned = (ConditionedFlowState<T>) state;
			ModuleResponse<Boolean> conditionResult = Modules.executeModule(conditioned.getConditionOfChoice(), message);
			if(!conditionResult.isOk()){
				throw new RuntimeException("Error on module");
			}
			FlowState<?> nextState = conditionResult.getResponse() ? conditioned.ifTrue() : conditioned.ifFlase();
			conditioned.setNext(nextState);
		}
		return response;
		
	}
	
}
