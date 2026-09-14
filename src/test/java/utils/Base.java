package utils;

import com.google.common.base.FinalizablePhantomReference;
import org.openqa.selenium.WebDriver;

public class Base {

    static final WebDriver driver = BrowserFactory.startBrowser("firefox","https://ndosisimplifiedautomation.vercel.app");

}
