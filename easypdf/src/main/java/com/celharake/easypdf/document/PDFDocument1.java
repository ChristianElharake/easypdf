package com.celharake.easypdf.document;

import com.celharake.easypdf.annotation.Document;
import com.celharake.easypdf.annotation.FieldMapping;
import com.celharake.easypdf.datamodel.DocumentCode;
import com.celharake.easypdf.datamodel.LanguageCode;

@Document(documentCode = DocumentCode.CODE1, languageCode = LanguageCode.FR_FR)
@Document(documentCode = DocumentCode.CODE1, languageCode = LanguageCode.EN_US)
public class PDFDocument1 {
	
	@FieldMapping(key = "Champ1", languageCode = LanguageCode.FR_FR)
	@FieldMapping(key = "Field1", languageCode = LanguageCode.EN_US)
	public String getFirstName(){
		return "";
	}
	
	@FieldMapping(key = "Champ1", languageCode = LanguageCode.FR_FR)
	@FieldMapping(key = "Field1", languageCode = LanguageCode.EN_US)
	public String getLastName(){
		return "";
	}

}
