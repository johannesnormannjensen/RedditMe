package com.redditme.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Johannes on 11-12-2015.
 */
public class JsonNodeUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String jsonNodeToString(JsonNode jsonNode) {
        String submissionAsString = null;
        try {
            submissionAsString = mapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return submissionAsString;
    }

    public static JsonNode stringToJsonNode(String string) {
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

}
