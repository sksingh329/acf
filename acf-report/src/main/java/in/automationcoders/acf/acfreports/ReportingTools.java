package in.automationcoders.acf.acfreports;

public interface ReportingTools {
    void saveReport();
    void createTestInReport(String testName);
    void reportLog(String methodName, ReportStatus reportStatus, String message);
}
