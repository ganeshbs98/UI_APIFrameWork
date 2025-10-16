package com.demo.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.List;

public class ReadCsvFile {
    public static void main(String[] args) throws IOException, CsvException {
        //code to read the csv file in java

//        File file=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"testData"+File.separator+"LoginCred.csv");
//        FileReader fr=new FileReader(file);
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData"+File.separator+"LoginCred.csv");
        InputStreamReader isr=new InputStreamReader(is);
        CSVReader csvReader=new CSVReader(isr);
        List<String[]> allData=csvReader.readAll();
        for(String[] dataArray:allData){
            for(String data:dataArray){
                System.out.print(data+" ");
            }
            System.out.println();
        }

    }
}
