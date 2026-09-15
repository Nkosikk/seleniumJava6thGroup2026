package utils;

import org.openqa.selenium.WebDriver;

public class base {
    static WebDriver driver = BrowserFactory.startBrowser("firefox", "https://ndosisimplifiedautomation.vercel.app");

}
