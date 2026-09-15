package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class browserFactory {
    static WebDriver driver;
    public static WebDriver startBrowser(String browserChoice, String url){
        if (browserChoice.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();

        } else if (browserChoice.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();

        } else if (browserChoice.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        } else  {
            driver = new EdgeDriver();
        }
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
    }

}
