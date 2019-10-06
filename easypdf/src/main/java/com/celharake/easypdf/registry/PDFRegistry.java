package com.celharake.easypdf.registry;

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
import com.celharake.easypdf.annotation.Field;
import com.celharake.easypdf.annotation.Fields;
import com.celharake.easypdf.dto.enumeration.DocumentCode;
import com.celharake.easypdf.dto.enumeration.LanguageCode;

@Component
public class PDFRegistry {

	private Map<String, Map<Field,PDFRegistryContextBean>> fieldMap = new HashMap<>();

	@Autowired
	private ApplicationContext context;

	
	public Map<Field, PDFRegistryContextBean> getFields(DocumentCode documentCode, LanguageCode languageCode){
		return fieldMap.getOrDefault(getKey(documentCode, languageCode), Collections.emptyMap());
	}
 	
	@PostConstruct
	public void init() {
		registerFields();
	}

	private void registerFields() {
		for (Entry<String, Object> entrySet : context.getBeansWithAnnotation(Document.class).entrySet()) {
			Class<? extends Object> clazz = entrySet.getValue().getClass();
			Document document = clazz.getAnnotation(Document.class);
			for (Method method : clazz.getDeclaredMethods()) {
				Fields fields = method.getAnnotation(Fields.class);
				if(fields != null) {
					for (Field field : fields.value()) {
						addField(document, method, entrySet.getValue(), field);
					}
				}
				Field field = method.getAnnotation(Field.class);
				if(field != null) {
					addField(document, method, entrySet.getValue(), field);
				}
			}
		}
	}

	private void addField(Document document, Method method, Object instance, Field field) {
		String key = getKey(document.documentCode(), field.languageCode());
		Map<Field, PDFRegistryContextBean> mapMethod = fieldMap.getOrDefault(key, new HashMap<Field, PDFRegistryContextBean>());
		mapMethod.put(field, new PDFRegistryContextBean(instance, method));
		fieldMap.putIfAbsent(key, mapMethod);
	}

	public static String getKey(DocumentCode documentCode, LanguageCode languageCode) {
		return documentCode.name().concat("_").concat(languageCode.name());
	}
}
