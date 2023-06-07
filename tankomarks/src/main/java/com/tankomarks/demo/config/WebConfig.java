/*
package com.tankomarks.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tankomarks.demo.converter.DemografiaConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/
	private final DemografiaConverter demografiaConverter;

    public WebConfig(DemografiaConverter demografiaConverter) {
        this.demografiaConverter = demografiaConverter;
    }
    /

    @Override
    public void addFormatters(FormatterRegistry registry) {
    	
        registry.addConverter(new DemografiaConverter()); //demografiaConverter
        
    }
    
}
*/