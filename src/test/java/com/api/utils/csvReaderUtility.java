package com.api.utils;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.UserPojo;
import com.dataproviders.beans.CreateJobBean;
import com.dataproviders.beans.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import kotlin.collections.ArrayDeque;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class csvReaderUtility {
    private csvReaderUtility(){

    }

    public static <T> Iterator<T> loadCsv(String PathforCsvFile,Class<T> bean){
        InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream(PathforCsvFile);
        InputStreamReader isr=new InputStreamReader(is);
        CSVReader csvReader=new CSVReader(isr);

        CsvToBean<T> csvToBean=new CsvToBeanBuilder(csvReader).withType(bean).withIgnoreEmptyLine(true).build();
        List<T> List=csvToBean.parse();
        return List.iterator();
    }
}
