package jira.api;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.gson.JsonSyntaxException;

import jira.api.login.AuthorizePage;
import jira.api.login.Cloud;
import jira.api.login.GetAccessTokenRequest;
import jira.api.login.GetAccessTokenResponse;
import jira.api.login.LoginPageDesktop;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static jira.api.APICommonUtils.*;

public class APIService {
	private static final Logger logger = LogManager.getLogger(APIService.class);

	// TODO - ask i this CODE TOKEN and CloudID, should be here? i think it's ok
	// because they serve(service)
	private static String CODE;
	private static String TOKEN;
	private static String CloudID;

	public static void login() {
		CODE = getCodeFromURL();
		TOKEN = getAccessToken(CODE);
		CloudID = getCloudID(TOKEN);
	}

	public static String getCodeFromURL() {
		logger.info("getting CODE from url");
//		WebDriver driver = useHeadlessDriver();
		WebDriver driver = new ChromeDriver();

		LoginPageDesktop loginPageDesktop = new LoginPageDesktop(driver);
		AuthorizePage authorizePage = loginPageDesktop.login();
		authorizePage.login();
		String code = authorizePage.getCODE();
		logger.info("got CODE from url");
		return code;
	}

	public static String getAccessToken(String CODE) {
		logger.info("getting TOKEN from server");
		String BASE_TOKEN_URL = "https://auth.atlassian.com/oauth/token";
		GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest(CODE);
		RequestBody body = RequestBody.create(gson.toJson(getAccessTokenRequest), JSON);
		Request request = new Request.Builder().url(BASE_TOKEN_URL).post(body).build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.code() == 200) {
				logger.info("got TOKEN response from server");
			}
		} catch (IOException e) {
			logger.error("error in getting TOKEN from server", e);
		}
		return "Bearer " + getTokenFromResponse(response);
	}

	public static String getCloudID(String TOKEN) {
		logger.info("getting Cloud-ID from server");
		String BASE_CLOUD_URL = "https://api.atlassian.com/oauth/token/accessible-resources";

		Request request = new Request.Builder().url(BASE_CLOUD_URL).addHeader("Authorization", TOKEN)
				.addHeader("Accept", "application/json").build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.code() == 200) {
				logger.info("got Cloud-ID from server");
			}
		} catch (IOException e) {
			logger.error("error in getting Cloud-ID from server", e);
		}
		return getIDFromCloudResponse(response);
	}

	private static String getIDFromCloudResponse(Response response) {
		ResponseBody responseBody = response.body();
		String jsonString = null;
		try {
			jsonString = responseBody.string();
		} catch (IOException e) {
			logger.error("error while parsing response body", e);
		}
		// next line remove the "[" "]" from response
		jsonString = jsonString.substring(1, jsonString.length() - 1);

		Cloud responseCloud = gson.fromJson(jsonString, Cloud.class);
		return responseCloud.getId();
	}

	private static String getTokenFromResponse(Response response) {
		ResponseBody responseBody = response.body();
		GetAccessTokenResponse getAccessTokenResponse = null;
		try {
			getAccessTokenResponse = gson.fromJson(responseBody.string(), GetAccessTokenResponse.class);
		} catch (JsonSyntaxException | IOException e) {
			logger.error("problem while gettin TOKEN from response", e);
		}
		return getAccessTokenResponse.getAccess_token();
	}

	public static WebDriver useHeadlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
