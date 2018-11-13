package com.viettel.erp.utils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectMapperProvider {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ObjectMapperProvider.class);
	ObjectMapper mapper;

	public ObjectMapperProvider() {
		LOGGER.info("Creating object mapper class");
		mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}
	
	public ObjectMapper getMapper() {
		return mapper;
	}
}
