package com.celharake.easypdf.structure;

import static com.celharake.easypdf.dto.enumeration.DocumentCode.CODE1;
import static com.celharake.easypdf.dto.enumeration.LanguageCode.EN_US;
import static com.celharake.easypdf.dto.enumeration.LanguageCode.FR_FR;

import org.springframework.stereotype.Component;

import com.celharake.easypdf.annotation.Document;
import com.celharake.easypdf.annotation.Field;

@Component
@Document(documentCode = CODE1)
public class PDFDocument1 {
	
	@Field(name = "First name of witness", languageCode = FR_FR)
	@Field(name = "Field1", languageCode = EN_US)
	public String getFirstName(){
		return "First name of witness";
	}
	
	@Field(name = "Middle name of witness", languageCode = FR_FR)
	public String getLastName(){
		return "Middle name of witness";
	}
	
	@Field(name = "Last name of witness", languageCode = FR_FR)
	public String getAddress(){
		return "Last name of witness";
	}

}
