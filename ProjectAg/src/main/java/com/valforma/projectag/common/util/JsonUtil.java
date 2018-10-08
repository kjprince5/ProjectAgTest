package com.valforma.projectag.common.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	public static Object parse(String json) {
		try {
			return objectMapper.readValue(json, Object.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toString(Object json) {
		try {
			return objectMapper.writeValueAsString(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
