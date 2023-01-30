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

public class LoginPageDesktop {
	private String BASE_CODE_URL = "https://auth.atlassian.com/authorize?audience=api.atlassian.com&client_id=EMcZzazmRdqdGmD48zjmCD3tVielmpwN&scope=read:jira-work read:account read:me&redirect_uri=https%3A%2F%2Ftask-day.onrender.com%2F&response_type=code&prompt=consent";
	private WebDriver driver;
	private String CODE;

	private static WebElement usernameElement;
	private static By usernameLocator = By.className("css-wxvfrp");

	private static WebElement passwordElement;
	private static By passwordLocator = By.name("password");

	private static WebElement continueButton;
	private static By contintueLocator = By.xpath("//span[text()='Continue']");

	private static WebElement loginButton;
	private static By loginLocator = By.xpath("//span[text()='Log in']");

	private static WebElement chooseElements;
	private static By chooseLocator = By.xpath("//*[text()='Choose a site']");

	private static WebElement acceptButton;
	private static By acceptLocator = By.xpath("//*[text()='Accept']");

	private static WebElement automatValueElement;
	private static By automatValueLocator = By.xpath("//*[text()='automat-ct.atlassian.net']");

	public LoginPageDesktop(WebDriver driver) {
		this.driver = driver;
	}

	public void login() throws InterruptedException {
		driver.get(BASE_CODE_URL);

		usernameElement = waitForElementByName(this.driver, usernameLocator);
		setUsername("itzikv3@gmail.com");

		continueButton = waitForElementByName(this.driver, contintueLocator);
		continueButton.click();

		loginButton = waitForElementByName(this.driver, loginLocator);
		passwordElement = waitForElementByName(this.driver, passwordLocator);

		setPassword("itzikpass");
		loginButton.click();

		chooseElements = waitForElementByName(this.driver, chooseLocator);
		chooseElements.click();

		automatValueElement = waitForElementByName(this.driver, automatValueLocator);
		automatValueElement.click();

		acceptButton = waitForElementByName(this.driver, acceptLocator);
		Thread.sleep(2000);
		acceptButton.click();

		waitForTitleChange(this.driver, "Taskday");

		CODE = getCodeFromUrl(this.driver);
		driver.close();
	}

	public void setUsername(String username) {
		usernameElement.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public String getCODE() {
		return CODE;
	}

	public static WebElement waitForElementByName(WebDriver driver, By by) {
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

	private static String getCodeFromUrl(WebDriver driver) {
		String url = driver.getCurrentUrl();
		int hashIndex = url.indexOf("#");
		return url.substring(url.indexOf("code=") + 5, hashIndex);
	}
}
