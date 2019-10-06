package com.celharake.easypdf.writer;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PreDestroy;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.celharake.easypdf.annotation.Field;
import com.celharake.easypdf.dto.enumeration.DocumentCode;
import com.celharake.easypdf.dto.enumeration.LanguageCode;
import com.celharake.easypdf.registry.PDFRegistry;
import com.celharake.easypdf.registry.PDFRegistryContextBean;

@Component
public class PDFWriter {

	private static final String PATH = "src/main/resources/files/";

	@Autowired
	private PDFRegistry context;

	@PreDestroy
	public void load() {
		load(DocumentCode.CODE1, LanguageCode.FR_FR);
	}

	public void load(DocumentCode documentCode, LanguageCode languageCode) {
		try {
			Map<Field, PDFRegistryContextBean> fields = context.getFields(documentCode, languageCode);
			String pdfFile = PDFRegistry.getKey(documentCode, languageCode).concat(".pdf");
			PDDocument pdfDocument = PDDocument.load(new File(PATH.concat(pdfFile)));
			PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
			PDAcroForm acroForm = docCatalog.getAcroForm();
			for (Entry<Field, PDFRegistryContextBean> entrySet : fields.entrySet()) {
				Field field = entrySet.getKey();
				PDField pdField = acroForm.getField(field.name());
				switch (field.type()) {
				case TEXTBOX:
					if (pdField != null) {
						PDFRegistryContextBean wrapper = entrySet.getValue();
						Object value = wrapper.getMethod().invoke(wrapper.getInstance());
						pdField.setValue((String) value);
					} else {
						System.err.println("No field found with name:" + field.name());
					}
					break;
				case CHECKBOX:
				case RADIOBUTTON:
					if (pdField != null) {
						PDFRegistryContextBean wrapper = entrySet.getValue();
						boolean value = (boolean) wrapper.getMethod().invoke(wrapper.getInstance());
						if (value)
							((PDCheckBox) pdField).check();
						else
							((PDCheckBox) pdField).unCheck();

					} else {
						System.err.println("No field found with name:" + field.name());
					}
					break;
				default:
					break;
				}
			}
			pdfDocument.save("output.pdf");
			pdfDocument.close();
		} catch (Exception e) {
		}
	}
}
