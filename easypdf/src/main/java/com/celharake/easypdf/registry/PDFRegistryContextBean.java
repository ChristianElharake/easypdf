package com.celharake.easypdf.registry;

import java.lang.reflect.Method;

public class PDFRegistryContextBean {

	private Object instance;
	private Method method;

	public PDFRegistryContextBean(Object instance, Method method) {
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
