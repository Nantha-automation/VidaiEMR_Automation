package com.EMR.utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        try {
            // Load configuration.properties from classpath under /properties/
            String resourcePath = "/properties/configuration.properties";

            // Read file from classpath to avoid hardcoded filesystem paths
            InputStream input = ConfigurationReader.class.getResourceAsStream(resourcePath);
            if (input == null) {
                throw new RuntimeException("Configuration file not found on classpath: " + resourcePath);
            }

            // properties can store data (properties) in key/value format
            properties = new Properties();

            // load the values from input to the properties object
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String keyName){
        return properties.getProperty(keyName);
    }


}
