package unitTests;

import in.automationcoders.acf.acfreports.ReportLogger;
import in.automationcoders.acf.acfreports.ReportStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportTest {
    @Test
    public void listenerTest(){
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        ReportLogger.log(methodName, ReportStatus.INFO,"This is info");
        System.out.println("This is inside test");
    }
    @Test
    public void listenerTest2(){
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        ReportLogger.log(methodName, ReportStatus.PASS,"This is info");
        System.out.println("This is inside test");
        Assert.assertTrue(true);
    }
    @Test
    public void listenerTest3(){
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName() +"." + Thread.currentThread().getStackTrace()[1].getMethodName();
        ReportLogger.log(methodName, ReportStatus.FAIL,"This is info");
        System.out.println("This is inside test");
        Assert.assertFalse(true);
    }
}
