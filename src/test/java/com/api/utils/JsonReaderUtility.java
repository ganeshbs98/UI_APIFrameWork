package com.api.utils;


import com.api.pojo.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonReaderUtility {
    public static <T>Iterator<T> jsonUtility(String filepath,Class<T[]> clazz)  {
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
        ObjectMapper objMapper=new ObjectMapper();
        T[] classArray;
        List<T> list=null;
        try{
            classArray=objMapper.readValue(is, clazz);
            list=Arrays.asList(classArray);
        }catch (IOException e){
            e.printStackTrace();
        }
        return list.iterator();


    }
}
