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
import com.example.modular.modules.configuration.FlowConfiguration;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.configuration.StatesFlowConfiguration;
import com.example.modular.modules.configuration.impl.TransformationModuleConfigurationImpl;
import com.example.modular.modules.configuration.states.impl.FlowStateImpl;
import com.example.modular.modules.datamodel.BasicMessage;
import com.example.modular.modules.datamodel.Message;
import com.example.modular.repository.FlowsRepository;
import com.example.modular.repository.ModulesRepository;
import com.example.modular.repository.ScriptsRepository;
import com.example.modular.repository.datamodel.Flow;
import com.example.modular.repository.datamodel.Module;
import com.example.modular.repository.datamodel.ModulePosition;
import com.example.modular.repository.datamodel.Script;

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
	ModulesRepository modulesRepo;
	@Autowired
	FlowsRepository flows;
	@Autowired
	FlowExecutorService fes;
	
	@Test
	public void statesFlowTest(){
		TransformationModuleConfigurationImpl module = new TransformationModuleConfigurationImpl(ModuleType.JS,null);
		module.setScript(SCRIPT_METADATA.getBytes());
		FlowStateImpl state1 = new FlowStateImpl(null, module);
		
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
		Script script = new Script();
		script.setContent(SCRIPT_METADATA.getBytes());
		scripts.save(script);
		Module moduleBase = new Module();
		moduleBase.setType(ModuleType.JS.name());
		moduleBase.setScriptId(script.getId());
		moduleBase.setKind(ModuleKind.TRANSFORMATION.name());
		modulesRepo.save(moduleBase);
		//////////////
		Script script1 = new Script();
		script1.setContent(SCRIPT_1.getBytes());
		scripts.save(script1);
		Module moduleBase1 = new Module();
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
