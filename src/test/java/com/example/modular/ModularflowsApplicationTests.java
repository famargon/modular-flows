package com.example.modular;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.modular.executor.FlowExecutorService;
import com.example.modular.modules.ModuleKind;
import com.example.modular.modules.ModuleType;
import com.example.modular.modules.configuration.ConfigurationService;
import com.example.modular.modules.configuration.FlowConfiguration;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.StatesFlowConfiguration;
import com.example.modular.modules.configuration.impl.TransformationModuleConfigurationImpl;
import com.example.modular.modules.configuration.states.impl.FlowStateImpl;
import com.example.modular.modules.datamodel.BasicMessage;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.repository.FlowsRepository;
import com.example.modular.repository.ModuleConfigurationsRepository;
import com.example.modular.repository.ScriptsRepository;
import com.example.modular.repository.datamodel.DTOModuleConfiguration;
import com.example.modular.repository.datamodel.DTOScript;
import com.example.modular.repository.datamodel.v1.Flow;
import com.example.modular.repository.datamodel.v1.ModulePosition;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners( DependencyInjectionTestExecutionListener.class)
public class ModularflowsApplicationTests {

	private static final String SCRIPT = "(function(){ \n var text = 'Hola mundo';\nreturn 'resultado script '+text;\n}())";
	private static final String SCRIPT_METADATA = "var metadata = {text:'asdb',id:3321321}; metadata;";
	private static final String SCRIPT_1 = "var metadata = {text:'script 1',ref:'script1'}; metadata;";
	
	@Autowired
	ScriptsRepository scripts;
	@Autowired
	ModuleConfigurationsRepository modulesRepo;
	@Autowired
	FlowsRepository flows;
	@Autowired
	FlowExecutorService fes;
	@Autowired
	ConfigurationService configService;
	
	@Test
	public void statesFlowTest(){
		TransformationModuleConfigurationImpl module = new TransformationModuleConfigurationImpl(ModuleType.JS,null);
		module.setScript(SCRIPT_METADATA.getBytes());
		FlowStateImpl state1 = new FlowStateImpl(configService,null, module);

		StatesFlowConfiguration config = new StatesFlowConfiguration();
		config.setInitialState(state1);
		
		Message message = new BasicMessage();
		message.getMetadata().put("text", "hello");
		message.getMetadata().put("ref", "hello");
		Message result = fes.execute(config, message);
		
		Map<String,String> metadata = result.getMetadata();
		assertTrue(metadata.get("text").equals("asdb"));
		assertNotNull(metadata.get("id"));
	}
	
	@Test
	public void complexStatesFlowTest(){
		TransformationModuleConfigurationImpl module1 = new TransformationModuleConfigurationImpl(ModuleType.JS,null);
		module1.setScript(SCRIPT_1.getBytes());
		
		TransformationModuleConfigurationImpl module2 = new TransformationModuleConfigurationImpl(ModuleType.JS,null);
		module2.setScript(SCRIPT_METADATA.getBytes());
		
		FlowStateImpl state1 = new FlowStateImpl(configService,null, module1);

		FlowStateImpl state2 = new FlowStateImpl(configService,state1, module2);

		state1.setNext(state1);
		
		StatesFlowConfiguration config = new StatesFlowConfiguration();
		config.setInitialState(state1);
		
		Message message = new BasicMessage();
		message.getMetadata().put("text", "hello");
		message.getMetadata().put("ref", "hello");
		Message result = fes.execute(config, message);
		
		Map<String,String> metadata = result.getMetadata();
		assertTrue(metadata.get("text").equals("asdb"));
		assertTrue(metadata.get("ref").equals("script1"));
		assertNotNull(metadata.get("id"));
	}
	
	@Test
	public void inMemoryFlowTest(){
		FlowConfiguration configuration = new FlowConfiguration();
		List<ModuleConfiguration<?>> modules = new LinkedList<>();
		configuration.setModulesSequence(modules);
		TransformationModuleConfigurationImpl module = new TransformationModuleConfigurationImpl(ModuleType.JS,null);
		module.setScript(SCRIPT_METADATA.getBytes());
		modules.add(module);
		Message message = new BasicMessage();
		message.getMetadata().put("text", "hello");
		message.getMetadata().put("ref", "hello");
		assertNull(message.getMetadata().get("id"));
		Message result = fes.execute(configuration, message);
		
		Map<String,String> metadata = result.getMetadata();
		assertTrue(metadata.get("text").equals("asdb"));
		assertNotNull(metadata.get("id"));
	
	}
	
	@Test
	public void databaseFlowTest(){
		DTOScript script = new DTOScript();
		script.setContent(SCRIPT_METADATA.getBytes());
		scripts.save(script);
		DTOModuleConfiguration moduleBase = new DTOModuleConfiguration();
		moduleBase.setType(ModuleType.JS.name());
		moduleBase.setScriptId(script.getId());
		moduleBase.setKind(ModuleKind.TRANSFORMATION.name());
		modulesRepo.save(moduleBase);
		//////////////
		DTOScript script1 = new DTOScript();
		script1.setContent(SCRIPT_1.getBytes());
		scripts.save(script1);
		DTOModuleConfiguration moduleBase1 = new DTOModuleConfiguration();
		moduleBase1.setType(ModuleType.JS.name());
		moduleBase1.setScriptId(script1.getId());
		moduleBase1.setKind(ModuleKind.TRANSFORMATION.name());
		modulesRepo.save(moduleBase1);
		//////////////
		Flow flow = new Flow();
		List<ModulePosition> modulesPos = new ArrayList<>();
		flow.setModules(modulesPos);
		ModulePosition pos2 = new ModulePosition();
		pos2.setModuleId(moduleBase.getId());
		pos2.setPosition(2);
		modulesPos.add(pos2);
		ModulePosition pos1 = new ModulePosition();
		pos1.setModuleId(moduleBase1.getId());
		pos1.setPosition(1);
		modulesPos.add(pos1);
		flows.save(flow);
		
		Message message = new BasicMessage();
		message.getMetadata().put("text", "hello");
		message.getMetadata().put("ref", "hello");
		Message result = fes.execute(flow.getId(), message);
		
		Map<String,String> metadata = result.getMetadata();
		assertTrue(metadata.get("text").equals("asdb"));
		assertTrue(metadata.get("ref").equals("script1"));
		assertNotNull(metadata.get("id"));
	}

}
