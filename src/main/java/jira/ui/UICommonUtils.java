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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	// wait for specific title of web page. return true if happen, else false.
	public static boolean waitForExpectedTitle(WebDriver driver, String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.titleIs(expectedTitle));
	}

	// get secret code that generate from url
	public static String getCodeFromUrl(WebDriver driver) {
		String url = driver.getCurrentUrl();
		int hashIndex = url.indexOf("#");
		return url.substring(url.indexOf("code=") + 5, hashIndex);
	}
	
	//chrome driver "without" not really open the browser, work behind.
	public static WebDriver getHeadlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
