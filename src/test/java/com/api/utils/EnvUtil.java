package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvUtil {
    private static final Logger logger= LogManager.getLogger(EnvUtil.class);
     private static Dotenv dotenv;
     static{
         logger.info("Loading environment variables from .env file");
         dotenv= Dotenv.load();
     }
    private EnvUtil() {
        // Private constructor to prevent instantiation
    }

    public static String getEnvValue(String envKey){
         logger.info("Retrieving value for environment variable: "+envKey);
        return dotenv.get(envKey);

    }
}
