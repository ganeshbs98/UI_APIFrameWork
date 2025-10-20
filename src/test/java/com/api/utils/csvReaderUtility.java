package com.api.utils;

import com.api.pojo.UserPojo;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class csvReaderUtility {
    private csvReaderUtility(){

    }

    public static Iterator<UserPojo> loadCsv(String PathforCsvFile){
        InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream(PathforCsvFile);
        InputStreamReader isr=new InputStreamReader(is);
        CSVReader csvReader=new CSVReader(isr);
        CsvToBean<UserPojo> csvToBean=new CsvToBeanBuilder(csvReader).withType(UserPojo.class).withIgnoreEmptyLine(true).build();
        List<UserPojo> userList=csvToBean.parse();
        return userList.iterator();
    }
}
