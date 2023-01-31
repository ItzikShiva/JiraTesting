package jira.api;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class APICommonUtils {
	public static Gson gson = new Gson();
	public static OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public static WebElement waitForElementBy(WebDriver driver, By by) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(Exception.class);

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
	}

	public static boolean waitForTitleChange(WebDriver driver, String expectedTitle) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.titleIs(expectedTitle));
	}

	public static String getCodeFromUrl(WebDriver driver) {
		String url = driver.getCurrentUrl();
		int hashIndex = url.indexOf("#");
		return url.substring(url.indexOf("code=") + 5, hashIndex);
	}
}
