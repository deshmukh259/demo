package com.springboot.demo.serializer;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SnakeToCamelCaseSerializer extends JsonSerializer<Map<String, Object>> {
    @Override
    public void serialize(Map<String, Object> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            String camelCaseKey = toCamelCase(entry.getKey());
            Object entryValue = entry.getValue();
            // Handle nested maps recursively
            if (entryValue instanceof Map) {
                entryValue = convertNestedMap((Map<String, Object>) entryValue);
            }
            gen.writeFieldName(camelCaseKey);
            gen.writeObject(entryValue);
        }
        gen.writeEndObject();
    }

    private Map<String, Object> convertNestedMap(Map<String, Object> snakeCaseMap) {
        Map<String, Object> camelCaseMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : snakeCaseMap.entrySet()) {
            String camelCaseKey = toCamelCase(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Map) {
                value = convertNestedMap((Map<String, Object>) value);
            }
            camelCaseMap.put(camelCaseKey, value);
        }
        return camelCaseMap;
    }

    private String toCamelCase(String snakeCase) {
        String[] parts = snakeCase.split("_");
        StringBuilder camelCase = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].toLowerCase();
            camelCase.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
        }
        return camelCase.toString();
    }
}