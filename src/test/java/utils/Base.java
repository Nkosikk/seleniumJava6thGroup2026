package utils;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

public class Base {

    static WebDriver driver = BrowserFactory.startBrowser("firefox", "https://ndosisimplifiedautomation.vercel.app");
    public HomePage homepage = new HomePage(driver);
    public LoginPage loginpage = new LoginPage(driver);


}
