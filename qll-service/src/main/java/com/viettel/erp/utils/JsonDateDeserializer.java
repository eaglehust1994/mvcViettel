package com.viettel.erp.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {
		
		try {
			return DateUtils.parseDate(jsonparser.getText(), "dd/MM/yyyy", "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","");
		} catch (ParseException e) {
			throw new IOException(e);
		}
	}
}
