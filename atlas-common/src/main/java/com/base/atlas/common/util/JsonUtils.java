package com.base.atlas.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * @author CaiJie Pang
 * @since 2023/1/31
 */
public class JsonUtils {

  private JsonUtils(){}

  private static final ObjectMapper objectMapper;
  static {
    objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.ALWAYS);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
  }

  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      return null;
    }
  }

  public static <T> T toObject(String jsonString, Class<T> classType) {
    try {
      return objectMapper.readValue(jsonString, classType);
    } catch (JsonProcessingException e) {
      return null;
    }
  }
}
