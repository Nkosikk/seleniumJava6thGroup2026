package utils;

import com.google.common.base.FinalizablePhantomReference;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class Base {

    static final WebDriver driver = BrowserFactory.startBrowser("firefox","https://ndosisimplifiedautomation.vercel.app");
    public HomePage homepage = new HomePage(driver);
}
