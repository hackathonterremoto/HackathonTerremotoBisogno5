package org.hackathon.soluzione5.model.converter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DateJsonDeserializer extends JsonDeserializer<Date> {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException {

		try {
			return  dateFormat.parse(arg0.getText());
		} catch (ParseException e) {
			throw new IOException(e.getMessage());
		}

	}

}
