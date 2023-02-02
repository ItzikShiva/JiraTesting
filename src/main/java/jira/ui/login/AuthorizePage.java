package jira.ui.login;

import static jira.ui.UICommonUtils.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthorizePage extends BasePage {
	private static final Logger logger = LogManager.getLogger(AuthorizePage.class);
	private static WebElement chooseElements;
	private static WebElement acceptButton;
	private static WebElement automatValueElement;

	private static By chooseElementsLocator = By.xpath("//*[text()='Choose a site']");
	private static By acceptButtonLocator = By.xpath("//*[text()='Accept']");
	private static By automatValueElementLocator = By.xpath("//*[text()='automat-ct.atlassian.net']");

	private String code;

	public AuthorizePage(WebDriver driver) {
		super(driver);
	}

	public void login() {
		chooseElements = waitForElementByLocator(driver, chooseElementsLocator);
		chooseElements.click();

		automatValueElement = waitForElementByLocator(driver, automatValueElementLocator);
		automatValueElement.click();

		ClickAcceptButton();

		waitForExpectedTitle(driver, "Taskday");
		logger.info("login and authorization successful");
		code = getCodeFromUrl(driver);
		driver.close();
	}

	public String getCode() {
		return code;
	}

	private void ClickAcceptButton() {
		acceptButton = waitForElementByLocator(driver, acceptButtonLocator);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("error in ACCEPT button loading (login proccess)", e);
		}
		acceptButton.click();
	}
}
