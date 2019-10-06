package com.celharake.easypdf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.celharake.easypdf.dto.enumeration.LanguageCode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Renderers.class)
public @interface Renderer {

	LanguageCode languageCode();

}
