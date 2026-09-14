package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    By verifyHomePageContent = By.xpath("//div[@class='nav-items']/button/span[contains(text(),'Home')]");


}
