package com.urbanladder.utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtility {

    // Reads JSON file and returns JSONObject
    public static JSONObject readJson(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieves value for a given key
    public static String getValue(String filePath, String key) {
        JSONObject json = readJson(filePath);
        return json != null ? json.optString(key) : null;
    }
}
