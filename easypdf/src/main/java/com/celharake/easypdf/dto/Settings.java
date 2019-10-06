package com.celharake.easypdf.dto;

import com.celharake.easypdf.dto.enumeration.DocumentCode;
import com.celharake.easypdf.dto.enumeration.LanguageCode;

public class Settings {

	private LanguageCode languageCode;
	
	private DocumentCode documentCode;

	public LanguageCode getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(LanguageCode languageCode) {
		this.languageCode = languageCode;
	}

	public DocumentCode getDocumentCode() {
		return documentCode;
	}

	public void setDocumentCode(DocumentCode documentCode) {
		this.documentCode = documentCode;
	}
	
}
