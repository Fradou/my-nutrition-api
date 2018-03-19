package com.fradou.nutrition.config;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring dispatcher servlet and mapping initialization
 *
 */
public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringAppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] { "/" };
	}
	
	/**
	 * Custom config to have an exception thrown if no corresponding handler
	 * /mapping. Allow custom 404 management to return json for the API part.
	 */
    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }
    
}
