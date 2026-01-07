package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Condition;

public class ConfigManager {
    private static final Logger logger= LogManager.getLogger(ConfigManager.class);
    private static Properties prop = new Properties();
    private static String path = "config.properties";
    private static String env;

    private ConfigManager() {

    }

    static {
        logger.info("Reading the env value passed from terminal");
        System.getProperty("env");
        if(System.getProperty("env")==null){
            logger.warn("No env value is set...., hence defaulting to QA env");
            env = System.getProperty("env","qa");
        }
        env=env.toLowerCase().trim();
        logger.info("Running in Environment: "+env);
        switch (env) {
            case "qa" -> path = "config.qa.properties";

            case "dev" -> path = "config.dev.properties";

            case "uat" -> path = "config.uat.properties";

            default -> path = "config.properties";
        }
        logger.info("Loading the property file: "+path);
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        if (input == null) {
            logger.error("properites file not found in given filepath");
            throw new RuntimeException("Unable to find config.properties file");
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            logger.error("Cannot find the file in given filepath");
            throw new RuntimeException(e);
        }
    }

    public static Properties loadProperties() {
        return prop;
    }
    public static String getProperty(String key){
        logger.info("Fetching the value for the key from properties file: "+key);
        return prop.getProperty(key);
    }
//    public static String loadProperties(String PropKey) {
//        return prop.getProperty(PropKey.toUpperCase());
//    }
}
