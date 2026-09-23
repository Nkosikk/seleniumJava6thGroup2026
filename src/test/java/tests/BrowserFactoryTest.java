package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BrowserFactory;

public class BrowserFactoryTest {

    private WebDriver driver;
    private static final String TEST_URL = "https://ndosisimplifiedautomation.vercel.app";

    @BeforeClass
    public void setup() {
        System.out.println("Starting BrowserFactory Tests...");
    }

    @Test(priority = 1)
    public void testChromeDriverInitialization() {
        driver = BrowserFactory.startBrowser("chrome", TEST_URL);
        
        Assert.assertNotNull(driver, "Driver should not be null for Chrome");
        Assert.assertTrue(driver instanceof ChromeDriver, "Should create ChromeDriver for 'chrome' choice");
        Assert.assertNotNull(driver.getCurrentUrl(), "Driver should have navigated to URL");
        Assert.assertTrue(driver.getCurrentUrl().contains("ndosisimplifiedautomation"), 
            "Driver should navigate to the correct URL");
    }

    @Test(priority = 2)
    public void testFirefoxDriverInitialization() {
        driver = BrowserFactory.startBrowser("firefox", TEST_URL);
        
        Assert.assertNotNull(driver, "Driver should not be null for Firefox");
        Assert.assertTrue(driver instanceof FirefoxDriver, "Should create FirefoxDriver for 'firefox' choice");
        Assert.assertNotNull(driver.getCurrentUrl(), "Driver should have navigated to URL");
    }

    @Test(priority = 3)
    public void testEdgeDriverInitialization() {
        driver = BrowserFactory.startBrowser("edge", TEST_URL);
        
        Assert.assertNotNull(driver, "Driver should not be null for Edge");
        Assert.assertTrue(driver instanceof EdgeDriver, "Should create EdgeDriver for 'edge' choice");
        Assert.assertNotNull(driver.getCurrentUrl(), "Driver should have navigated to URL");
    }

    @Test(priority = 4)
    public void testSafariDriverInitialization() {
        try {
            driver = BrowserFactory.startBrowser("safari", TEST_URL);
            Assert.assertNotNull(driver, "Driver should not be null for Safari");
            Assert.assertTrue(driver instanceof SafariDriver, "Should create SafariDriver for 'safari' choice");
        } catch (Exception e) {
            System.out.println("Safari driver not available on this machine: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testDefaultBrowserIsEdge() {
        driver = BrowserFactory.startBrowser("invalid", TEST_URL);
        
        Assert.assertNotNull(driver, "Driver should not be null for invalid choice");
        Assert.assertTrue(driver instanceof EdgeDriver, 
            "Should default to EdgeDriver for invalid browser choice");
    }

    @Test(priority = 6)
    public void testCaseInsensitiveBrowserChoice() {
        driver = BrowserFactory.startBrowser("CHROME", TEST_URL);
        
        Assert.assertNotNull(driver, "Driver should not be null");
        Assert.assertTrue(driver instanceof ChromeDriver, 
            "Should accept uppercase browser choice 'CHROME'");
    }

    @Test(priority = 7)
    public void testMixedCaseBrowserChoice() {
        driver = BrowserFactory.startBrowser("FiRefoX", TEST_URL);
        
        Assert.assertNotNull(driver, "Driver should not be null");
        Assert.assertTrue(driver instanceof FirefoxDriver, 
            "Should accept mixed case browser choice 'FiRefoX'");
    }

    @Test(priority = 8)
    public void testWindowMaximization() {
        driver = BrowserFactory.startBrowser("chrome", TEST_URL);
        
        org.openqa.selenium.Dimension windowSize = driver.manage().window().getSize();
        Assert.assertNotNull(windowSize, "Window size should be retrievable");
        Assert.assertTrue(windowSize.getHeight() > 0 && windowSize.getWidth() > 0, 
            "Window should be maximized with positive dimensions");
    }

    @Test(priority = 9)
    public void testURLNavigation() {
        driver = BrowserFactory.startBrowser("chrome", TEST_URL);
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL should not be null");
        Assert.assertTrue(currentUrl.contains("ndosisimplifiedautomation"), 
            "Should navigate to the provided URL");
    }

    @Test(priority = 10)
    public void testMultipleBrowserInstances() {
        WebDriver driver1 = BrowserFactory.startBrowser("chrome", TEST_URL);
        WebDriver driver2 = BrowserFactory.startBrowser("firefox", TEST_URL);
        
        Assert.assertNotNull(driver1, "First driver should not be null");
        Assert.assertNotNull(driver2, "Second driver should not be null");
        Assert.assertTrue(driver1 instanceof ChromeDriver, "First driver should be Chrome");
        Assert.assertTrue(driver2 instanceof FirefoxDriver, "Second driver should be Firefox");
        
        driver = driver2;
        driver1.quit();
    }

    @Test(priority = 11)
    public void testBrowserFactoryReturnValue() {
        WebDriver returnedDriver = BrowserFactory.startBrowser("chrome", TEST_URL);
        
        Assert.assertNotNull(returnedDriver, "BrowserFactory should return a valid WebDriver");
        Assert.assertTrue(returnedDriver instanceof WebDriver, "Returned object should be WebDriver instance");
        
        driver = returnedDriver;
    }

    @Test(priority = 12)
    public void testEmptyUrlHandling() {
        try {
            driver = BrowserFactory.startBrowser("chrome", "");
            Assert.assertNotNull(driver, "Driver should be created even with empty URL");
        } catch (Exception e) {
            System.out.println("Empty URL caused exception (expected behavior): " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Browser instance closed successfully");
            } catch (Exception e) {
                System.out.println("Error closing browser: " + e.getMessage());
            }
        }
    }
}
