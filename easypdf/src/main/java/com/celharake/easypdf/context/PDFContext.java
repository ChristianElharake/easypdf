package com.celharake.easypdf.context;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.celharake.easypdf.annotation.Document;
import com.celharake.easypdf.annotation.FieldMapping;
import com.celharake.easypdf.annotation.FieldsMapping;
import com.celharake.easypdf.datamodel.DocumentCode;
import com.celharake.easypdf.datamodel.LanguageCode;

@Component
public class PDFContext {

	private Map<String, Map<String,PDFContextWrapper>> map = new HashMap<>();

	@Autowired
	private ApplicationContext context;

	
	public Map<String, PDFContextWrapper> getFields(DocumentCode documentCode, LanguageCode languageCode){
		return map.getOrDefault(getKey(documentCode, languageCode), Collections.emptyMap());
	}
 	
	@PostConstruct
	public void init() {
		for (Entry<String, Object> entrySet : context.getBeansWithAnnotation(Document.class).entrySet()) {
			Class<? extends Object> clazz = entrySet.getValue().getClass();
			Document document = clazz.getAnnotation(Document.class);
			for (Method method : clazz.getDeclaredMethods()) {
				FieldsMapping fields = method.getAnnotation(FieldsMapping.class);
				if(fields != null) {
					for (FieldMapping fieldMapping : fields.value()) {
						addField(document, method, entrySet.getValue(), fieldMapping);
					}
				}
				FieldMapping fieldMapping = method.getAnnotation(FieldMapping.class);
				if(fieldMapping != null) {
					addField(document, method, entrySet.getValue(), fieldMapping);
				}
			}
		}
		
		for (Entry<String, Map<String, PDFContextWrapper>> entryset : map.entrySet()) {
			System.err.println(entryset.getKey());
			for (Entry<String, PDFContextWrapper> method : entryset.getValue().entrySet()) {
				System.err.println("\t" + method.getKey());
			}
		}
	}

	private void addField(Document document, Method method, Object instance, FieldMapping fieldMapping) {
		String key = getKey(document.documentCode(), fieldMapping.languageCode());
		Map<String, PDFContextWrapper> mapMethod = map.getOrDefault(key, new HashMap<String, PDFContextWrapper>());
		mapMethod.put(fieldMapping.key(), new PDFContextWrapper(instance, method));
		map.putIfAbsent(key, mapMethod);
	}

	public static String getKey(DocumentCode documentCode, LanguageCode languageCode) {
		return documentCode.name().concat("_").concat(languageCode.name());
	}
}
