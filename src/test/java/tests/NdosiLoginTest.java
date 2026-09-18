package tests;

import org.testng.annotations.Test;
import utils.Base;

public class NdosiLoginTest extends Base {

    @Test
    public void loginTest(){
        homepage.checkIfNdosiWebsiteIsLoaded();
        homepage.clickHomeLoginButton();


    }
}
