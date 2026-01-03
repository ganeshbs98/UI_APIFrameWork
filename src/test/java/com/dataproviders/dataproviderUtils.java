package com.dataproviders;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.*;
import com.database.dao.createJobPayloadDataDao;
import com.dataproviders.beans.CreateJobBean;
import com.dataproviders.beans.UserBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class dataproviderUtils {
    private static final Logger logger= LogManager.getLogger(dataproviderUtils.class);



    @DataProvider(name="LoginApiDataProvider",parallel = true)
    public static Iterator<UserBean> LoginApiDataProvider(){
        logger.info("Loading Login API data from CSV file and returning list of Iterator");
        return csvReaderUtility.loadCsv("testData/LoginCred.csv", UserBean.class);
    }
    @DataProvider(name="CreateJobAPIDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> creatJobDataProvider(){
        logger.info("Executing the Create Job API CSV Data Provider");

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
        logger.info("Executing the Create Job API Faker Data Provider");

        String FakerCount=System.getProperty("fakerCount","5");
        int FakerCountInt=Integer.parseInt(FakerCount);
        Iterator<CreateJobPayload>payloadIterator=FakeDataGenerator.generateFakeCreateJobData(FakerCountInt);
        return payloadIterator;
    }

    @DataProvider(name="LoginApiJsonDataProvider",parallel = true)
    public static Iterator<UserCredentials> LoginApiJsonDataProvider(){
        logger.info("Executing the Create Job API JSON Data Provider");

        return JsonReaderUtility.jsonUtility("testData/LoginApiJsonData.json", UserCredentials[].class);
    }
    @DataProvider(name="CreateJobAPiJsonDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> CreateJobAPiJsonDataProvider(){
        return JsonReaderUtility.jsonUtility("testData/CreateJobApi.json", CreateJobPayload[].class);
    }

    @DataProvider(name="CreateJobAPiExcelDataProvider",parallel = true)
    public static Iterator<UserCredentials> CreateJobAPiExcelDataProvider(){
        logger.info("Executing the Create Job API Excel Data Provider");
        return ExcelUtil.LoadExcelTestData();
    }

    @DataProvider(name="CreateJobAPiExcelDataProviderWithPoiji",parallel = true)
    public static Iterator<UserBean> CreateJobAPiExcelDataProviderWithPoiji(){
        logger.info("Executing the Create Job API Excel Data Provider with POIJi");

        return ExcelUtilwithPoiji.LoadExcelTestData("testData/ExcelUtil.xlsx", UserBean.class,"LoginTestData");
    }



    @DataProvider(name="CreateJobAPiDataBaseDataProvider",parallel = true)
    public static Iterator<CreateJobPayload> CreateJobAPiDataBaseDataProvider(){
        logger.info("Executing the Create Job API DataBase Data Provider");

        List<CreateJobBean> list= createJobPayloadDataDao.getCreateJobPayloadData();
        List<CreateJobPayload>payloadList=new ArrayList<>();
        for (CreateJobBean bean:list){
            CreateJobPayload payload=createJobMapperUtility.mapper(bean);
            payloadList.add(payload);
        }
        return payloadList.iterator();
    }

}
