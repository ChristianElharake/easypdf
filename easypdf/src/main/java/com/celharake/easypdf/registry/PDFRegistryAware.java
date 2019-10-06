package com.celharake.easypdf.registry;

import java.util.List;

public interface PDFRegistryAware {

	void onDocumentKeyLoaded(List<String> keys);
	
}
