package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshots {

    private static final String screenshotDir = System.getProperty("user.dir") + File.separator + "NdosiReports" + File.separator + "Screenshots";

    public static String takeSnapShot(WebDriver driver, String screenshotName) throws IOException {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationDir = new File(screenshotDir);

        if (!destinationDir.exists() && !destinationDir.mkdirs()) {
            throw new IOException("Unable to create screenshot directory: " +destinationDir.getAbsolutePath());
        }
        File destination = new File(destinationDir, screenshotName + ".png");

        FileUtils.copyFile(src, destination);


        return destination.getAbsolutePath();
    }

}
