package com.myapps.libraryapp_db.test.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestUtil {
	private ObjectMapper objectMapper;
	
	public TestUtil() {
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
	}
	
	public String mapToJson(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, clazz);
	}
}
