package in.automationcoders.acf.acfreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtentReport implements ReportingTools{
    private static final Logger logger = LogManager.getLogger(ExtentReport.class);

    private static ExtentReport extentReportInstance;
    private String reportFileName;
    public String getReportFileName(){
        return reportFileName;
    }

    private ExtentReports report;

    private final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    private ExtentReport(String filePath, String filePrefix, String timestamp,String reportName,String testerName){
        logger.info("Constructor is called");
        createReportFile(filePath,filePrefix,timestamp,reportName,testerName);
    }

    public static ExtentReport getInstance(String filePath, String filePrefix, String timestamp,String reportName,String testerName){
        if(extentReportInstance == null)
            extentReportInstance = new ExtentReport(filePath,filePrefix,timestamp,reportName,testerName);
        logger.info("Current Instance Id is: {}",extentReportInstance);
        return extentReportInstance;
    }

    private void createReportFile(String filePath, String filePrefix, String timestamp, String reportName, String testerName) {
        reportFileName = filePath+filePrefix+timestamp+".html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(filePrefix+"_"+timestamp);
        extentSparkReporter.config().setTheme(Theme.DARK);
        report = new ExtentReports();
        report.attachReporter(extentSparkReporter);
        report.setSystemInfo("Tester",testerName);
        logger.info("Report file is created: {}",reportFileName);
    }

    @Override
    public void createTestInReport(String testName) {
        ExtentTest test = report.createTest(testName);
        extentTestThreadLocal.set(test);
    }

    @Override
    public void reportLog(String methodName, ReportStatus reportStatus, String message) {
        String logMessage = methodName+"</br>"+message;
        if(reportStatus == ReportStatus.PASS){
            logger.info("Status - PASS, Message - {}",logMessage);
            extentTestThreadLocal.get().pass(logMessage);
        }
        else if(reportStatus == ReportStatus.FAIL){
            logger.info("Status - FAIL, Message - {}",logMessage);
            extentTestThreadLocal.get().fail(logMessage);
        }
        else if(reportStatus == ReportStatus.INFO){
            logger.info("Status - INFO, Message - {}",logMessage);
            extentTestThreadLocal.get().info(logMessage);
        }
        else if(reportStatus == ReportStatus.SKIP){
            logger.info("Status - SKIP, Message - {}",logMessage);
            extentTestThreadLocal.get().skip(logMessage);
        }
    }

    @Override
    public void saveReport(){
        report.flush();
        logger.info("Report file is saved, file name {}",reportFileName);
    }

}
