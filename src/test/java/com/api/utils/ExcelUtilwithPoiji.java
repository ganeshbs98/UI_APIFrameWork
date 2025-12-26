package com.api.utils;

import com.api.pojo.UserCredentials;
import com.dataproviders.beans.UserBean;
import com.poiji.bind.Poiji;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtilwithPoiji {

    private ExcelUtilwithPoiji() {

    }

    public static <T>Iterator<T> LoadExcelTestData(String fileName,Class<T> clazz,String sheetname) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        XSSFWorkbook xssfbook = null;
        try {
            xssfbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int totalsheet = xssfbook.getNumberOfSheets();
        XSSFRow row;
        XSSFCell mycell;
        int usernameIndex = -1;
        int passwordIndex = -1;
        List<T> datalist;
//        String name = xssfbook.getSheetName(0);
        XSSFSheet mySheet=xssfbook.getSheet(sheetname);
        datalist = Poiji.fromExcel(mySheet, clazz);
        return datalist.iterator();

    }
}
