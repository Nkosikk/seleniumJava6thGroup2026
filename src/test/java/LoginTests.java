import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTests {

    WebDriver driver;

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://ndosisimplifiedautomation.vercel.app");
        Thread.sleep(2000);
    }

    @Test
    public void clickLoginButtonTests() {
        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")).click();
    }

    @Test(dependsOnMethods = "clickLoginButtonTests")
    public void enterUsernameTests(){
        driver.findElement(By.id("login-email")).sendKeys("njceles@gmail.com");
    }

    @Test(dependsOnMethods = "enterUsernameTests")
    public void enterPasswordTests() {
        driver.findElement(By.id("login-password")).sendKeys("@12345678");
    }

    @Test(dependsOnMethods = "enterPasswordTests")
    public void clickSubmitButtonTests() throws InterruptedException {
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = "clickSubmitButtonTests")
    public void verifyLoginSuccessTests() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/div[1]/h2/span[1]")).isDisplayed();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
