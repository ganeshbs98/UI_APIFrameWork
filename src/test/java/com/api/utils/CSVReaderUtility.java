package com.api.utils;

import com.dataProvider.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReaderUtility {

    // constructor will be private
    //methods are static

    public static void loadCsv(String pathOfCsvFile) {
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
        InputStreamReader isr=new InputStreamReader(is);
        CSVReader csvReader=new CSVReader(isr);
        CsvToBean<UserBean> csvToBean=new CsvToBeanBuilder(csvReader).withType(UserBean.class).withIgnoreEmptyLine(true).build();
        List<UserBean> userList=csvToBean.parse();
        System.out.println(userList.get(0).getUsername());
        System.out.println(userList);

    }
}
