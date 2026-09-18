package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;


    By verifyHomePageContent_xpath = By.xpath("//div[@class='nav-items']/button/span[contains(text(),'Home')]");
    By mainLoginButton_xpath = By.xpath("//div[@class='nav-user-section']/button/span[contains(text(),'Login')]");

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    public void checkIfNdosiWebsiteIsLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(verifyHomePageContent_xpath));
        driver.findElement(verifyHomePageContent_xpath).isDisplayed();
    }
    public void clickHomeLoginButton(){
        driver.findElement(mainLoginButton_xpath).click();
    }

}
