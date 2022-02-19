package in.automationcoders.acf.acfreports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started");
        //TODO - Create html report file with timestamp
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed");
        //TODO - Log all message collected and make test as passed
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
        //TODO - Log all message collected and make test as failed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //TODO - Log all message collected and make test as skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
