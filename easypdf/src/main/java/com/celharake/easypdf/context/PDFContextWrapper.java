package com.celharake.easypdf.context;

import java.lang.reflect.Method;

public class PDFContextWrapper {

	private Object instance;
	private Method method;

	public PDFContextWrapper(Object instance, Method method) {
		super();
		this.instance = instance;
		this.method = method;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
