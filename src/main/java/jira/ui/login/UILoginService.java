package jira.ui.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static jira.ui.UICommonUtils.*;

//TODO - when there will be more methods, it will move to "UiService". but now it's only server the login
public class UILoginService {
	private static final Logger logger = LogManager.getLogger(UILoginService.class);

	//get secret code from first step of login . can use headless by changing the uncomment and comment of WebDriver
	public static String getSecretCode() {
		logger.info("getting code from url");
		WebDriver driver = getHeadlessDriver();
//		WebDriver driver = new ChromeDriver();

		LoginPageWeb loginPageDesktop = new LoginPageWeb(driver);
		AuthorizePage authorizePage = loginPageDesktop.login();
		authorizePage.login();
		String code = authorizePage.getCode();
		logger.info("got code from url");
		return code;
	}
}
