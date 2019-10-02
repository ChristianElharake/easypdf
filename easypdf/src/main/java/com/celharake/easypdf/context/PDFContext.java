package com.celharake.easypdf.context;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.celharake.easypdf.annotation.Document;

@Component
public class PDFContext {

	private static final Logger log = LoggerFactory.getLogger(PDFContext.class);
	
	@Autowired
	private ApplicationContext context;
	
	@PostConstruct
	public void init() {
		 for (Entry<String, Object> entrySet : context.getBeansWithAnnotation(Document.class).entrySet()) {
			Document documentAnnotation = entrySet.getValue().getClass().getAnnotation(Document.class);
			
		}
	}
}
