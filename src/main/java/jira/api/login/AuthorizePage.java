package jira.api.login;

import static jira.api.APICommonUtils.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthorizePage {
	private static final Logger logger = LogManager.getLogger(AuthorizePage.class);

	private WebDriver driver;
	private String CODE;

	private static WebElement chooseElements;
	private static By chooseLocator = By.xpath("//*[text()='Choose a site']");

	private static WebElement acceptButton;
	private static By acceptLocator = By.xpath("//*[text()='Accept']");

	private static WebElement automatValueElement;
	private static By automatValueLocator = By.xpath("//*[text()='automat-ct.atlassian.net']");

	public AuthorizePage(WebDriver driver) {
		this.driver = driver;
	}

	public void login() {
		chooseElements = waitForElementBy(driver, chooseLocator);
		chooseElements.click();

		automatValueElement = waitForElementBy(driver, automatValueLocator);
		automatValueElement.click();

		ClickAcceptButton();

		waitForTitleChange(driver, "Taskday");
		logger.info("login and authorization successful");
		CODE = getCodeFromUrl(driver);
		driver.close();
	}

	public String getCODE() {
		return CODE;
	}

	private void ClickAcceptButton() {
		acceptButton = waitForElementBy(driver, acceptLocator);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("error in ACCEPT button loading (login proccess)", e);
		}
		acceptButton.click();
	}
}
