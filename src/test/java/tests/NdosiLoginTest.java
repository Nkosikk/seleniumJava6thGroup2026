package tests;


import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.ExcelReader;
import utils.Base;

public class NdosiLoginTest extends Base {

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return ExcelReader.getLoginDataFromExcel("src/test/java/testdata/data.xlsx", "loginDetails");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password){
        homepage.checkIfNdosiWebsiteIsLoaded();
        homepage.clickHomeLoginButton();
        loginpage.enterUsername(email);
        loginpage.enterPassword(password);
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
