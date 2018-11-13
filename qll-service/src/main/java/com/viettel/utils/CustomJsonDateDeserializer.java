package com.viettel.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		String dateStr = jsonparser.getText();
		
		if (StringUtils.isNotEmpty(dateStr)) {
			try {
				return (Date) formatter.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		} else {
			return null;
		}
	}
}
