package com.celharake.easypdf.document;

import static com.celharake.easypdf.datamodel.DocumentCode.CODE1;
import static com.celharake.easypdf.datamodel.LanguageCode.EN_US;
import static com.celharake.easypdf.datamodel.LanguageCode.FR_FR;

import org.springframework.stereotype.Component;

import com.celharake.easypdf.annotation.Document;
import com.celharake.easypdf.annotation.FieldMapping;

@Component
@Document(documentCode = CODE1)
public class PDFDocument1 {
	
	@FieldMapping(key = "First name of witness", languageCode = FR_FR)
	@FieldMapping(key = "Field1", languageCode = EN_US)
	public String getFirstName(){
		return "First name of witness";
	}
	
	@FieldMapping(key = "Middle name of witness", languageCode = FR_FR)
	public String getLastName(){
		return "Middle name of witness";
	}
	
	@FieldMapping(key = "Last name of witness", languageCode = FR_FR)
	public String getAddress(){
		return "Last name of witness";
	}

}
