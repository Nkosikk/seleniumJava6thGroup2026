package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    By username_id = By.id("login-email");
    By password_id = By.id("login-password");
    By loginButton_id = By.id("login-submit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterUsername(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(username_id));
        driver.findElement(username_id).sendKeys(username);
    }
        public void enterPassword(String password) {
            driver.findElement(password_id).sendKeys(password);

        }

        public void clickLoginButton(){
        driver.findElement(loginButton_id).click();

    }

    }
