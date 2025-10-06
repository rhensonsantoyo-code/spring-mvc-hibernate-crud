package org.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Root context: persistence + service beans
        return new Class[] { PersistenceJPAConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Web context (controllers, view resolvers)
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}