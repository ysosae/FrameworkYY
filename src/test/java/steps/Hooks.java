package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.logging.Logger;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    Logger log = Logger.getLogger(String.valueOf(Hooks.class));
    Scenario scenario = null;

    @Before
    public void initDriver(Scenario scenario) throws IOException {
        log.info("***********************************************************************************************************");
        log.info("[ Configuration ] - Initializing driver configuration");
        log.info("***********************************************************************************************************");
        System.setProperty("webdriver.chrome.driver",
                "C:/FrameworkYY/src/test/lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920, 1080");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        this.scenario = scenario;
        log.info("***********************************************************************************************************");
        log.info("[ Scenario ] - " + scenario.getName());
        log.info("***********************************************************************************************************");
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            try {
//                scenario.write("The scenario failed.");
//                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//                scenario.embed(screenshot, "test-output/Screenshots");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

        log.info("***********************************************************************************************************");
        log.info("[ Driver Status ] - Clean and close the intance of the driver");
        log.info("***********************************************************************************************************");
        driver.quit();
    }
}
