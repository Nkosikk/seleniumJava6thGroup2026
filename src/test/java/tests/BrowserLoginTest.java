package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import java.time.Duration;

public class BrowserLoginTest {

    WebDriver driver;
    WebDriverWait wait;
    private static final String BASE_URL = "https://ndosisimplifiedautomation.vercel.app";
    private static final String VALID_EMAIL = "njceles@gmail.com";
    private static final String VALID_PASSWORD = "@12345678";

    @BeforeClass
    public void setup() {
        driver = BrowserFactory.startBrowser("chrome", BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testLoginButtonVisible() {
        WebElement loginButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")
            )
        );
        Assert.assertTrue(loginButton.isDisplayed(), "Login button should be visible");
    }

    @Test(priority = 2)
    public void testClickLoginButton() {
        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")
            )
        );
        loginButton.click();
        
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("login-email"))
        );
        Assert.assertTrue(emailField.isDisplayed(), "Email field should appear after clicking login");
    }

    @Test(priority = 3)
    public void testEnterValidEmail() {
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("login-email"))
        );
        emailField.clear();
        emailField.sendKeys(VALID_EMAIL);
        
        Assert.assertEquals(emailField.getAttribute("value"), VALID_EMAIL, 
            "Email should be entered correctly");
    }

    @Test(priority = 4)
    public void testEnterValidPassword() {
        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("login-password"))
        );
        passwordField.clear();
        passwordField.sendKeys(VALID_PASSWORD);
        
        Assert.assertFalse(passwordField.getAttribute("value").isEmpty(), 
            "Password field should not be empty");
    }

    @Test(priority = 5)
    public void testClickSubmitButton() {
        WebElement submitButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("login-submit"))
        );
        submitButton.click();
    }

    @Test(priority = 6, dependsOnMethods = "testClickSubmitButton")
    public void testLoginSuccess() {
        WebElement successElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2/span[1]")
            )
        );
        Assert.assertTrue(successElement.isDisplayed(), 
            "Success message should be visible after login");
    }

    @Test(priority = 7)
    public void testInvalidEmailLogin() {
        driver.navigate().to(BASE_URL);
        
        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")
            )
        );
        loginButton.click();
        
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("login-email"))
        );
        emailField.sendKeys("invalid@test.com");
        
        WebElement passwordField = driver.findElement(By.id("login-password"));
        passwordField.sendKeys(VALID_PASSWORD);
        
        WebElement submitButton = driver.findElement(By.id("login-submit"));
        submitButton.click();
        
        Assert.assertFalse(isElementPresent(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2/span[1]")),
            "Login should fail with invalid email");
    }

    @Test(priority = 8)
    public void testEmptyEmailLogin() {
        driver.navigate().to(BASE_URL);
        
        WebElement loginButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")
            )
        );
        loginButton.click();
        
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("login-email"))
        );
        Assert.assertTrue(emailField.isDisplayed(), "Email field should be visible");
        
        WebElement submitButton = driver.findElement(By.id("login-submit"));
        submitButton.click();
        
        Assert.assertFalse(isElementPresent(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2/span[1]")),
            "Login should fail with empty email");
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
