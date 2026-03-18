package com.company.mobile.utils;

import java.io.InputStream;
import java.util.Properties;

public class UtilsMethod {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = UtilsMethod.class.getClassLoader().getResourceAsStream("config.properties")){
            if (input == null) {
                throw new RuntimeException("File 'config.properties' not found");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load 'config.properties' files", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
