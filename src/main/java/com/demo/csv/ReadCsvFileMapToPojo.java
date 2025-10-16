package com.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadCsvFileMapToPojo {
    public static void main(String[] args) {
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData"+ File.separator+"LoginCred.csv");
        InputStreamReader isr=new InputStreamReader(is);
        CSVReader csvReader=new CSVReader(isr);
       CsvToBean<UserPojo> csvToBean=new CsvToBeanBuilder(csvReader).withType(UserPojo.class).withIgnoreEmptyLine(true).build();
        List<UserPojo> userList=csvToBean.parse();
        System.out.println(userList.get(0).getUsername());
        System.out.println(userList);




    }
}
