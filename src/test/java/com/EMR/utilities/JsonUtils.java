package com.EMR.utilities;

import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static JsonNode root;

    public static JsonNode getJsonData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String path = ConfigurationReader.get("testdata_path");   // GLOBAL PATH
            root = mapper.readTree(new File(path));
            return root;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON test data", e);
        }
    }

    public static String getValue(String parent, String child) {
        getJsonData();
        return root.get(parent).get(child).asText();
    }
    
}
