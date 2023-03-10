package jira.ui;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UICommonUtils {

    public static WebElement waitForElementByLocator(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * wait for specific title of web page. return true if happen, else false.
     */
    public static boolean waitForExpectedTitle(WebDriver driver, String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        return wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    /**
     * get secret code that generate from url
     */
    public static String getCodeFromUrl(WebDriver driver) {
        String url = driver.getCurrentUrl();
        int hashIndex = url.indexOf("#");
        return url.substring(url.indexOf("code=") + 5, hashIndex);
    }

    /**
     * return chrome driver that not really open the browser, work behind.
     */
    public static WebDriver getHeadlessDriver() {
        System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }
}
