package unitTests;

import in.automationcoders.acf.acfreports.ExtentReport;
import in.automationcoders.acf.acfreports.ReportStatus;
import in.automationcoders.acf.acfutils.FileHandlingUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class ExtentReportTest {
    ExtentReport extentReport;
    String reportFileName;
    String timestamp;

    @BeforeTest
    public void testSetup(){
        Calendar cal = Calendar.getInstance();
        timestamp = cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DAY_OF_MONTH)
                +"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE)+"_"+cal.get(Calendar.SECOND);
        extentReport = ExtentReport.getInstance(System.getProperty("user.dir")+"/output/reports/","ACF-Reports_",timestamp,"Test Automation Report","Unit Test");
        reportFileName = extentReport.getReportFileName();
    }
    @BeforeMethod
    public void setup(){
        reportFileName = extentReport.getReportFileName();
    }
    @Test
    public void validateReportFileName(){
        Assert.assertEquals(System.getProperty("user.dir")+"/output/reports/ACF-Reports_"+timestamp+".html",reportFileName);
    }
    @Test
    public void validateReportFileIsCreated(){
        extentReport.saveReport();
        File reportFile = new File(reportFileName);
        Assert.assertTrue(reportFile.exists());
    }
    @Test
    public void validatePassLogMessage(){
        String reportLog = "This step is passed";
        extentReport.createTestInReport("validatePassLogMessage");
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        extentReport.reportLog(methodName, ReportStatus.PASS,reportLog);
        extentReport.saveReport();
        String reportText = FileHandlingUtils.readFromFile(reportFileName);;
        Assert.assertTrue(reportText.contains(methodName));
        Assert.assertTrue(reportText.contains(reportLog));
    }
    @Test
    public void validateFailLogMessage(){
        String reportLog = "This step is failed";
        extentReport.createTestInReport("validateFailLogMessage");
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        extentReport.reportLog(methodName,ReportStatus.FAIL,reportLog);
        extentReport.saveReport();
        String reportText = FileHandlingUtils.readFromFile(reportFileName);;
        Assert.assertTrue(reportText.contains(methodName));
        Assert.assertTrue(reportText.contains(reportLog));
    }
    @Test
    public void validateInfoLogMessage(){
        String reportLog = "This step is info";
        extentReport.createTestInReport("validateInfoLogMessage");
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        extentReport.reportLog(methodName,ReportStatus.INFO,reportLog);
        extentReport.saveReport();
        String reportText = FileHandlingUtils.readFromFile(reportFileName);;
        Assert.assertTrue(reportText.contains(methodName));
        Assert.assertTrue(reportText.contains(reportLog));
    }
    @Test
    public void validateSkipLogMessage(){
        String reportLog = "This step is skipped";
        extentReport.createTestInReport("validateSkipLogMessage");
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        extentReport.reportLog(methodName,ReportStatus.SKIP,reportLog);
        extentReport.saveReport();
        String reportText = FileHandlingUtils.readFromFile(reportFileName);;
        Assert.assertTrue(reportText.contains(methodName));
        Assert.assertTrue(reportText.contains(reportLog));
    }
    @Test
    public void validateExtentReportIsSingleton(){
        String oldInstance = extentReport.toString();
        Calendar cal = Calendar.getInstance();
        String timestamp = cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DAY_OF_MONTH)
                +"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE)+"_"+cal.get(Calendar.SECOND);
        String newInstance = ExtentReport.getInstance(System.getProperty("user.dir")+"/reports/","ACF-Reports_",timestamp,"Test Automation Report","Unit Test").toString();

        Assert.assertEquals(oldInstance,newInstance);
    }
}
