package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    WebDriver driver;

    By dashboardHeader_xpath = By.xpath("//span[contains(text(),'Welcome')]");
    By menu_xpath = By.xpath("//span[contains(text(),'Menu')]");
    By logout_xpath = By.xpath("//span[contains(text(),'Logout')]");

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyLoginWasSuccessful(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader_xpath));
        driver.findElement(dashboardHeader_xpath).isDisplayed();
    }

    public void clickMenu(){
        driver.findElement(menu_xpath).click();
    }

    public void clickLogout(){
        driver.findElement(logout_xpath).click();
    }

    public void closeAlertWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
