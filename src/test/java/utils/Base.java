package utils;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;

public class Base {

    static WebDriver driver = BrowserFactory.startBrowser("chrome", "https://ndosisimplifiedautomation.vercel.app");
    public HomePage homepage = new HomePage(driver);
    public LoginPage loginpage = new LoginPage(driver);
    public DashboardPage dashboardpage = new DashboardPage(driver);


}
