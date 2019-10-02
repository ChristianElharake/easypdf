package com.celharake.easypdf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.celharake.easypdf.datamodel.DocumentCode;
import com.celharake.easypdf.datamodel.LanguageCode;

@Target(ElementType.TYPE)
@Repeatable(Documents.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Document {

	DocumentCode documentCode();
	
	LanguageCode languageCode();
}
