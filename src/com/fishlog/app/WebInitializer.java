package com.fishlog.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.fishlog.database.MongoDB;

public class WebInitializer implements WebApplicationInitializer {

	private final static int LOAD_ON_START = 1;


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = 
				new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		context.register(MongoDB.class);

		ServletRegistration.Dynamic dispatcher = 
				servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(LOAD_ON_START);
		dispatcher.addMapping("/");

	}

}