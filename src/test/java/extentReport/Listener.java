package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import utils.TakeScreenshots;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Driver;

import static utils.Base.driver;

public class Listener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " Has failed");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage() );

        try {
            String screenshotName = result.getMethod().getMethodName() + ".png";
            TakeScreenshots.takeSnapShot(driver, result.getMethod().getMethodName());
            extentTest.addScreenCaptureFromPath(Paths.get("Screenshots", screenshotName).toString().replace("\\", "/"), result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to take screenshot for test case: " + result.getMethod().getMethodName(), e);

        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " Has Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " Has been Skipped");
    }

    @Override
    public void onFinish(ITestContext result) {
        extent.flush();
    }

    @Override
    public void onStart(ITestContext result) {
        extent = ExtentReportManager.extentReports();
    }
}
