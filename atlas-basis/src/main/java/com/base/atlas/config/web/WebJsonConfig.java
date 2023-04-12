package com.base.atlas.config.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * null返回空字符串
 */
@Configuration
public class WebJsonConfig {

	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		// 忽略空值返回
//		objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
//		SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
//		serializerProvider.setNullValueSerializer(new JsonSerializer<Object>() {
//			@Override
//			public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
//					throws IOException {
//				jsonGenerator.writeString("");
//			}
//		});

		// Long 类型转换为 String 类型，避免js中数据长度溢出的问题
		SimpleModule module = new SimpleModule();
		module.addSerializer(Long.class, ToStringSerializer.instance);
		module.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(module);
		return objectMapper;
	}
}
