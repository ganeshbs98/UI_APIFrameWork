package com.api.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonReaderUtility {
    private static final Logger logger= LogManager.getLogger(JsonReaderUtility.class);

    public static <T>Iterator<T> jsonUtility(String filepath,Class<T[]> clazz)  {
        logger.info("Loading test data from JSON file:",filepath);
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
        ObjectMapper objMapper=new ObjectMapper();
        T[] classArray;
        List<T> list=null;
        try{
            logger.info("Converting the JSON data to POJO class objects",clazz,filepath);
            classArray=objMapper.readValue(is, clazz);
            list=Arrays.asList(classArray);
        }catch (IOException e){
            e.printStackTrace();
        }
        logger.info("Total records loaded: "+list.size());
        return list.iterator();


    }
}
