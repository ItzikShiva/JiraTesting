package jira.api;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class APILoginService {
	private static final Logger logger = LogManager.getLogger(APILoginService.class);

	private static String CODE;
	private static String TOKEN;
	private static String CloudID;
	public static APIService apiService = new APIService();

	@Test
	public static void login() throws IOException {
		CODE = getCodeFromURL();
		TOKEN = getTokenFromServer();
		CloudID = getCloudIdFromServer();
	}

	private static String getCodeFromURL() {
		logger.info("getting CODE from url");
		try {
			String code = apiService.getCodeURL();
			logger.info("got CODE from url");
			return code;
		} catch (InterruptedException | IOException e) {
			logger.error("error in getting CODE from url");
		}
		return "";
	}

	private static String getTokenFromServer() {
		logger.info("getting TOKEN from server");
		try {
			String token = apiService.getAccessToken(CODE);
			logger.info("got TOKEN from server");
			return token;
		} catch (IOException e) {
			logger.error("error in getting TOKEN from server");
		}
		return "";
	}

	private static String getCloudIdFromServer() {
		logger.info("getting Cloud-ID from server");
		try {
			String cloudID = apiService.getCloudID(TOKEN);
			logger.info("got Cloud-ID from server");
			return cloudID;
		} catch (IOException e) {
			logger.error("error in getting Cloud-ID from server");
		}
		return "";
	}

	public static String getTOKEN() {
		return TOKEN;
	}

//TODO - logout method
	public static void logout() {

	}
}
