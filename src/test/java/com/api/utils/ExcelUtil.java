package com.api.utils;

import com.api.pojo.UserCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
    private static final Logger logger= LogManager.getLogger(ExcelUtil.class);

    private ExcelUtil() {

    }

    public static Iterator<UserCredentials> LoadExcelTestData()  {
        List<UserCredentials> dataList = new ArrayList<>();
        logger.info("Loading test data from Excel file");
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/ExcelUtil.xlsx");
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
        for (int k = 0; k < totalsheet; k++) {
            String name = xssfbook.getSheetName(k);
            XSSFSheet my_Sheet = xssfbook.getSheet(name);
            XSSFRow headerRow = my_Sheet.getRow(k);
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("username")) {
                    usernameIndex = cell.getColumnIndex();
                } else if (cell.getStringCellValue().equalsIgnoreCase("password")) {
                    passwordIndex = cell.getColumnIndex();
                }
            }
            System.out.println("Username Index: " + usernameIndex + " Password Index: " + passwordIndex);
            int rowNum = my_Sheet.getLastRowNum();
            UserCredentials userCredentials;

            for (int i = 1; i <= rowNum; i++) {
                row = my_Sheet.getRow(i);
                userCredentials = new UserCredentials(row.getCell(usernameIndex).toString(), row.getCell(passwordIndex).toString());
                dataList.add(userCredentials);
            }

        }

        return dataList.iterator();


    }
}
