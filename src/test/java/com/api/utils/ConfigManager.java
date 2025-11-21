package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties prop = new Properties();
    private static String path = "config.properties";
    private static String env;

    private ConfigManager() {

    }

    static {
        env = System.getProperty("env","qa");
        env=env.toLowerCase().trim();
        System.out.println("Running in Environment: "+env);
        switch (env) {
            case "qa" -> path = "config.qa.properties";

            case "dev" -> path = "config.dev.properties";

            case "uat" -> path = "config.uat.properties";

            default -> path = "config.properties";
        }
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        if (input == null) {
            throw new RuntimeException("Unable to find config.properties file");
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties loadProperties() {
        return prop;
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
//    public static String loadProperties(String PropKey) {
//        return prop.getProperty(PropKey.toUpperCase());
//    }
}
