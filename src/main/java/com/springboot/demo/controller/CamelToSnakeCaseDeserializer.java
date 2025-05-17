package com.springboot.demo.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CamelToSnakeCaseDeserializer extends JsonDeserializer<Map<String, Object>> {
    @Override
    public Map<String, Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return convertNodeToMap(node);
    }

    private Map<String, Object> convertNodeToMap(JsonNode node) {
        Map<String, Object> result = new HashMap<>();
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String snakeCaseKey = toSnakeCase(field.getKey());
                JsonNode valueNode = field.getValue();
                Object value;
                if (valueNode.isObject()) {
                    value = convertNodeToMap(valueNode);
                } else if (valueNode.isArray()) {
                    value = convertArray(valueNode);
                } else if (valueNode.isTextual()) {
                    value = valueNode.asText();
                } else if (valueNode.isNumber()) {
                    value = valueNode.numberValue();
                } else if (valueNode.isBoolean()) {
                    value = valueNode.asBoolean();
                } else {
                    value = null;
                }
                result.put(snakeCaseKey, value);
            }
        }
        return result;
    }

    private Object convertArray(JsonNode arrayNode) {
        // Handle arrays if needed; for simplicity, return as-is or convert to List
        return arrayNode.toString();
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