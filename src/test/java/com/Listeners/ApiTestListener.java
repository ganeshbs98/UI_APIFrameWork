package com.Listeners;

import com.mysql.cj.conf.url.ReplicationDnsSrvConnectionUrl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ApiTestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(ApiTestListener.class);

    public void onTestStart(ITestResult result) {
        logger.info("*************************************************");
        logger.info("=====================Test Started:===================" + result.getName());
        logger.info("=================TestMethod================" + result.getMethod().getMethodName());
        logger.info("==========description==========" + result.getMethod().getDescription());
    }


    public void onTestSuccess(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        logger.info("***********************");
        logger.info("**********Test Passed:**********" + result.getName());
        logger.info("-------total Duration:-------" + (endTime - startTime) + " ms");
    }


    public void onTestFailure(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        logger.info("***********************");
        logger.error("**********Test FAILED!!!!:**********" + result.getName());
        logger.error("Error Message:" + result.getThrowable().getMessage());
        logger.info(result.getThrowable());
        logger.info("-------total Duration:-------" + (endTime - startTime) + " ms");
    }

    public void onTestSkipped(ITestResult result) {
        long startTime = result.getStartMillis();
        long endTime = result.getEndMillis();
        logger.info("***********************");
        logger.warn("**********Test SKIPPED!!!:**********" + result.getName());
        logger.info("-------total Duration:-------" + (endTime - startTime) + " ms");
    }

    public void onStart(ITestContext context) {
        logger.info("***********************");
        logger.info("**********Test Suite Started:**********");

    }


    public void onFinish(ITestContext context) {
        logger.info("***********************");
        logger.info("**********Test Suite Finished:**********");
    }
}
