package com.example.modular;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FlowsApplicationContext implements ApplicationContextAware {

	  private static ApplicationContext CONTEXT;

	  public void setApplicationContext(ApplicationContext context) throws BeansException {
	    CONTEXT = context;
	  }

	  public static <T> T getBean(Class<T> clazz) {
		  try {
			  return CONTEXT.getBean(clazz);
			  //
		  } catch (IllegalStateException ie) {
			  ((ConfigurableApplicationContext)CONTEXT).refresh();
		  }	  return CONTEXT.getBean(clazz);
	  }
	  
}
