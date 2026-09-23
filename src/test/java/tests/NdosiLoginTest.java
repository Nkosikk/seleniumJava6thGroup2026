package tests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.Base;

public class NdosiLoginTest extends Base {

    @Test
    public void loginTest(){
        homepage.checkIfNdosiWebsiteIsLoaded();
        homepage.clickHomeLoginButton();
        loginpage.enterUsername("admin@gmail.com");
        loginpage.enterPassword("@12345678");
        loginpage.clickLoginButton();
        dashboardpage.verifyLoginWasSuccessful();
        dashboardpage.clickMenu();
        dashboardpage.clickLogout();
        dashboardpage.closeAlertWindow();
    }


    @Test
    public void loginWithInvalidDetailsTest(){
        homepage.checkIfNdosiWebsiteIsLoaded();
        homepage.clickHomeLoginButton();
        loginpage.enterUsername("admin@gmail.com");
        loginpage.enterPassword("@hjgjhh");
        loginpage.clickLoginButton();
        dashboardpage.verifyLoginWasSuccessful();
        dashboardpage.closeAlertWindow();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
