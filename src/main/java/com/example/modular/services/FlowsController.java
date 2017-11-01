package com.example.modular.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.modular.executor.FlowExecutorService;
import com.example.modular.modules.datamodel.Message;

@RestController
public class FlowsController {

	@Autowired
	private FlowExecutorService flowsService;
	
	@RequestMapping(value="/execute/flow/{id}",method=RequestMethod.POST)
	public ResponseEntity<Message> execute(@PathVariable(name="id") String id, @RequestBody Message inputMessage){
		Message message = flowsService.execute(id, inputMessage);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
}
