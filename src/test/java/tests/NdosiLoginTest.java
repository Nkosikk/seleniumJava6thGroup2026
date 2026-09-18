package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Base;

public class NdosiLoginTest extends Base {

    @Test
    public void loginTest(){
        homepage.checkIfNdosiWebsiteIsLoaded();
        homepage.clickHomeLoginButton();
        loginpage.enterUsername("5372pamella@gmail.com");
        loginpage.enterPassword("@7654321");
        loginpage.clickLoginButton();


    }
}
