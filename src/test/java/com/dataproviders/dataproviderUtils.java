package com.dataproviders;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.UserCredentials;
import com.api.utils.FakeDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.api.utils.createJobMapperUtility;
import com.api.utils.csvReaderUtility;
import com.dataproviders.beans.CreateJobBean;
import com.dataproviders.beans.UserBean;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class dataproviderUtils {


    @DataProvider(name="LoginApiDataProvider",parallel = true)
    public static Iterator<UserBean> LoginApiDataProvider(){
        return csvReaderUtility.loadCsv("testData/LoginCred.csv", UserBean.class);
    }
    @DataProvider(name="CreateJobAPIDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> creatJobDataProvider(){
        Iterator<CreateJobBean>createJobBeanIterator= csvReaderUtility.loadCsv("testData/createJobData.csv", CreateJobBean.class);
        List<CreateJobPayload> payloadlist=new ArrayList<>();
        CreateJobBean tempBean=null;
        while(createJobBeanIterator.hasNext()){
             tempBean=createJobBeanIterator.next();
            CreateJobPayload payload=createJobMapperUtility.mapper(tempBean);
            payloadlist.add(payload);
        }
        return payloadlist.iterator();
    }
    @DataProvider(name="CreateJobAPIFakerDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> creatJobFakerDataProvider(){
        String FakerCount=System.getProperty("fakerCount","5");
        int FakerCountInt=Integer.parseInt(FakerCount);
        Iterator<CreateJobPayload>payloadIterator=FakeDataGenerator.generateFakeCreateJobData(FakerCountInt);
        return payloadIterator;
    }

    @DataProvider(name="LoginApiJsonDataProvider",parallel = true)
    public static Iterator<UserCredentials> LoginApiJsonDataProvider(){
        return JsonReaderUtility.jsonUtility("testData/LoginApiJsonData.json", UserCredentials[].class);
    }
    @DataProvider(name="CreateJobAPiJsonDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> CreateJobAPiJsonDataProvider(){
        return JsonReaderUtility.jsonUtility("testData/CreateJobApi.json", CreateJobPayload[].class);
    }

}
