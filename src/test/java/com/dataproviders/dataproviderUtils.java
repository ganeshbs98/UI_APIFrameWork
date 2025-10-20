package com.dataproviders;

import com.api.pojo.UserPojo;
import com.api.utils.csvReaderUtility;
import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class dataproviderUtils {


    @DataProvider(name="LoginApiDataProvider",parallel = true)
    public static Iterator<UserPojo> LoginApiDataProvider(){
        return csvReaderUtility.loadCsv("testData/LoginCred.csv");
    }

}
