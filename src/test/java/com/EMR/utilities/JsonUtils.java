package com.EMR.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    /**
     * Returns a list of strings from a JSON array under a given parent node.
     * Example: getArrayValues("registration", "searchBy")
     */
    public static List<String> getArrayValues(String parent, String arrayName) {
        getJsonData();
        List<String> list = new ArrayList<>();
        JsonNode parentNode = root.get(parent);
        if (parentNode == null) return list;
        JsonNode arrayNode = parentNode.get(arrayName);
        if (arrayNode == null || !arrayNode.isArray()) return list;
        Iterator<JsonNode> it = arrayNode.elements();
        while (it.hasNext()) {
            JsonNode n = it.next();
            list.add(n.asText());
        }
        return list;
    }

    /**
     * Returns a nested node by keys, e.g., getNestedNode("registration", "newRegistration")
     */
    public static JsonNode getNestedNode(String... keys) {
        getJsonData();
        JsonNode node = root;
        for (String key : keys) {
            if (node == null) return null;
            node = node.get(key);
        }
        return node;
    }
}
