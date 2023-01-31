package jira.api.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jira.api.APICommonUtils;

import static jira.api.APICommonUtils.*;

public class LoginPageDesktop {
	private static final Logger logger = LogManager.getLogger(LoginPageDesktop.class);

	// TODO - ask - Hod ther's driver declare here and in the authorizePage also. is
	// it ok?
	// (i feel like declare it once, but not sure, cause it's more readable like
	// this)
	private WebDriver driver;
	public static APICommonUtils apiCommonUtils = new APICommonUtils();

	private String BASE_CODE_URL = "https://auth.atlassian.com/authorize?audience=api.atlassian.com&client_id=EMcZzazmRdqdGmD48zjmCD3tVielmpwN&scope=read:jira-work read:account read:me&redirect_uri=https%3A%2F%2Ftask-day.onrender.com%2F&response_type=code&prompt=consent";

	private static WebElement usernameElement;
	private static By usernameLocator = By.className("css-wxvfrp");

	private static WebElement passwordElement;
	private static By passwordLocator = By.name("password");

	private static WebElement continueButton;
	private static By contintueLocator = By.xpath("//span[text()='Continue']");

	private static WebElement loginButton;
	private static By loginLocator = By.xpath("//span[text()='Log in']");

	public LoginPageDesktop(WebDriver driver) {
		logger.info("open\\start login page");
		driver.get(BASE_CODE_URL);
		this.driver = driver;
	}

	public AuthorizePage login() {
		logger.info("start login proccess");

		usernameElement = waitForElementBy(driver, usernameLocator);
		setUsername("itzikv3@gmail.com");

		continueButton = waitForElementBy(driver, contintueLocator);
		continueButton.click();

		loginButton = waitForElementBy(driver, loginLocator);
		passwordElement = waitForElementBy(driver, passwordLocator);

		setPassword("itzikpass");
		loginButton.click();
		logger.info("login successful going to authorization");

		return new AuthorizePage(driver);
	}

	public void setUsername(String username) {
		usernameElement.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordElement.sendKeys(password);
	}
}
