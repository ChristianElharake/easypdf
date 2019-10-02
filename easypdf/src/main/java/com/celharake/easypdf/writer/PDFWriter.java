package com.celharake.easypdf.writer;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PreDestroy;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.celharake.easypdf.context.PDFContext;
import com.celharake.easypdf.context.PDFContextWrapper;
import com.celharake.easypdf.datamodel.DocumentCode;
import com.celharake.easypdf.datamodel.LanguageCode;

@Component
public class PDFWriter {

	private static final String PATH = "src/main/resources/files/";

	@Autowired
	private PDFContext context;

	@PreDestroy
	public void load() {
		load(DocumentCode.CODE1, LanguageCode.FR_FR);
	}
	
	public void load(DocumentCode documentCode, LanguageCode languageCode) {
		try {
			Map<String, PDFContextWrapper> fields = context.getFields(documentCode, languageCode);
			String pdfFile = PDFContext.getKey(documentCode, languageCode).concat(".pdf");
			PDDocument pdfDocument = PDDocument.load(new File(PATH.concat(pdfFile)));
			PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
			PDAcroForm acroForm = docCatalog.getAcroForm();
			
			for (PDField field : acroForm.getFields()) {
				System.err.println(field.getFullyQualifiedName());
			}
			for (Entry<String, PDFContextWrapper> entrySet : fields.entrySet()) {
				PDField field = acroForm.getField(entrySet.getKey());
				if (field != null) {
					PDFContextWrapper wrapper = entrySet.getValue();
					Object value = wrapper.getMethod().invoke(wrapper.getInstance());
					field.setValue((String) value);
				} else {
					System.err.println("No field found with name:" + entrySet.getKey());
				}
			}
			pdfDocument.save("output.pdf");
			pdfDocument.close();
		} catch (Exception e) {
		}
	}
}
