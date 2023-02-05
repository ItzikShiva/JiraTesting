package jira.ui.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static jira.ui.UICommonUtils.*;

public class LoginPageWeb extends BasePage {
	private static final Logger logger = LogManager.getLogger(LoginPageWeb.class);

	private String baseCodeUrl;
	private static String username = "itzikv3@gmail.com";
	private static String password = "itzikpass";

	private static WebElement usernameElement;
	private static WebElement passwordElement;
	private static WebElement continueButton;
	private static WebElement loginButton;

	private static By usernameElementLocator = By.className("css-wxvfrp");
	private static By passwordElementLocator = By.name("password");
	private static By continueButtonLocator = By.xpath("//span[text()='Continue']");
	private static By loginButtonLocator = By.xpath("//span[text()='Log in']");

	public LoginPageWeb(WebDriver driver, String scope) {
		super(driver);
		setBaseCodeUrl(scope);
		logger.info("open\\start login page");
		driver.get(baseCodeUrl);
		this.driver = driver;
	}

	public AuthorizePage login() {
		return login(username, password);
	}

	public AuthorizePage login(String username, String password) {
		logger.info("start login proccess");

		usernameElement = waitForElementByLocator(driver, usernameElementLocator);
		setUsername(username);

		continueButton = waitForElementByLocator(driver, continueButtonLocator);
		continueButton.click();

		loginButton = waitForElementByLocator(driver, loginButtonLocator);
		passwordElement = waitForElementByLocator(driver, passwordElementLocator);

		setPassword(password);
		submit();
		if (waitForExpectedTitle(driver, "Authorize app")) {
			logger.info("first step login successful going to authorization");
		} else {
			logger.error("login not successful");
		}

		return new AuthorizePage(driver);
	}

	public void setUsername(String username) {
		usernameElement.sendKeys(username);
	}

	public void setPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void submit() {
		loginButton.click();
	}

	/*
	 * this method is an util for API login workaround. it called by the constructor
	 * for the base url login. it get "scope" as a parameter and update the base url
	 * for the login workaround.
	 */
	public void setBaseCodeUrl(String scope) {
		this.baseCodeUrl = "https://auth.atlassian.com/authorize?audience=api.atlassian.com&client_id=EMcZzazmRdqdGmD48zjmCD3tVielmpwN&scope="
				+ scope + "&redirect_uri=https%3A%2F%2Ftask-day.onrender.com%2F&response_type=code&prompt=consent";
	}
}
