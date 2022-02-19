package in.automationcoders.acf.acfreports;

import in.automationcoders.acf.acfreports.ReportStatus;
import org.testng.Reporter;

public class ReportLogger {
    public static void log(String methodName, ReportStatus status, String message){
        Reporter.log("methodName:= "+methodName+";status:="+status.toString()+";message:="+message);
    }
    public static void log(String methodName, ReportStatus status, String message, String screenshotFile){
        Reporter.log("methodName:= "+methodName+";status:="+status.toString()+";message:="+message+";screenshotFile:="+screenshotFile);
    }
}
