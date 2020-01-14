package com.sinotrans.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.SneakyThrows;
import lombok.var;

/**
 * json序列化
 * @author Admin
 *
 */
public class Jackson {
	
	/**
	 * json转java类
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SneakyThrows
	public static <T> T json2Obj(String json, Class<T> clazz) {
		var om = new ObjectMapper();
		om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om.readValue(json, clazz);
	}
	
	/**
	 * Java类转json
	 * @param obj
	 * @return
	 */
	@SneakyThrows
	public static String obj2Json(Object obj) {
		var om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		return om.writeValueAsString(obj);
	}
}
