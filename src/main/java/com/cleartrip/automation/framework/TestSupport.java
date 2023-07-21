package com.cleartrip.automation.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.Properties;

public class TestSupport {
    protected static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor executor;
    private static Properties properties;
    private static String browserType;
    private static String url;

    @BeforeSuite(alwaysRun = true)
    public void loadProperties() {
        System.out.println("Start of Test Suite");
        properties = Utility.getAutomationProperties();
        browserType = properties.getProperty("browser.type").toLowerCase();
        url = properties.getProperty("url");
    }

    @BeforeClass(alwaysRun = true)
    public void initialize_browser() {
        System.out.println("Executing: " + this.getClass().getSimpleName() + " Test Script");
        if (driver == null) {
            switch (browserType) {
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                case "chrome", default -> {
                    ChromeOptions options = new ChromeOptions();
                    options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                    options.setAcceptInsecureCerts(true);
                    options.addArguments("--start-maximized");
                    options.addArguments("--no-sandbox");
                    driver = new ChromeDriver(options);
                }
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        executor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterSuite(alwaysRun = true)
    public void endTestSuite() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
        System.out.println("Ending: " + this.getClass().getSimpleName() + " Test Script");

        properties.clear();
        System.out.println("End of the Test Suite");
    }
}
