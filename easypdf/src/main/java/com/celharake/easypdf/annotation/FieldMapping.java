package com.celharake.easypdf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.celharake.easypdf.datamodel.LanguageCode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(FieldsMapping.class)
public @interface FieldMapping {

	String key();
	
	LanguageCode languageCode();
}