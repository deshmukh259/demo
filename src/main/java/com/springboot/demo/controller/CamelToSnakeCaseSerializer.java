package com.springboot.demo.controller;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CamelToSnakeCaseSerializer extends JsonSerializer<Map<String, Object>> {
    @Override
    public void serialize(Map<String, Object> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            String snakeCaseKey = toSnakeCase(entry.getKey());
            Object entryValue = entry.getValue();
            // Handle nested maps recursively
            if (entryValue instanceof Map) {
                entryValue = convertNestedMap((Map<String, Object>) entryValue);
            }
            gen.writeFieldName(snakeCaseKey);
            gen.writeObject(entryValue);
        }
        gen.writeEndObject();
    }

    private Map<String, Object> convertNestedMap(Map<String, Object> camelCaseMap) {
        Map<String, Object> snakeCaseMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : camelCaseMap.entrySet()) {
            String snakeCaseKey = toSnakeCase(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Map) {
                value = convertNestedMap((Map<String, Object>) value);
            }
            snakeCaseMap.put(snakeCaseKey, value);
        }
        return snakeCaseMap;
    }

    private String toSnakeCase(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    snakeCase.append('_');
                }
                snakeCase.append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }
}
