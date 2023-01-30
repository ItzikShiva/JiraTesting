package jira.api;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIService {
	private static final Logger logger = LogManager.getLogger(APIService.class);

	// TODO - ask - i wrote logs in the class that use this APIService. but i think
	// instead of write logs there, i should write here because it's more depth. but
	// i'm not sure. what do you say?

	public static Gson gson = new Gson();
	public static OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public String getCloudID(String TOKEN) throws JsonSyntaxException, IOException {
		String BASE_CLOUD_URL = "https://api.atlassian.com/oauth/token/accessible-resources";

		Request request = new Request.Builder().url(BASE_CLOUD_URL).addHeader("Authorization", TOKEN)
				.addHeader("Accept", "application/json").build();

		Response response = client.newCall(request).execute();

		logIfWrongStatusCode(response);

		return getIDFromCloudResponse(response);
	}

	private static String getIDFromCloudResponse(Response response) throws IOException {
		ResponseBody responseBody = response.body();
		String jsonString = responseBody.string();
		// next line remove the "[" "]" from response
		jsonString = jsonString.substring(1, jsonString.length() - 1);

		Cloud responseCloud = gson.fromJson(jsonString, Cloud.class);
		return responseCloud.getId();
	}

	public String getAccessToken(String CODE) throws IOException {
		String BASE_TOKEN_URL = "https://auth.atlassian.com/oauth/token";
		GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest(CODE);

		RequestBody body = RequestBody.create(gson.toJson(getAccessTokenRequest), JSON);

		Request request = new Request.Builder().url(BASE_TOKEN_URL).post(body).build();
		Response response = client.newCall(request).execute();
		logIfWrongStatusCode(response);

		return "Bearer " + getTokenFromResponse(response);
	}

	private static String getTokenFromResponse(Response response) throws IOException {
		ResponseBody responseBody = response.body();
		GetAccessTokenResponse getAccessTokenResponse = gson.fromJson(responseBody.string(),
				GetAccessTokenResponse.class);
		return getAccessTokenResponse.getAccess_token();
	}

	public String getCodeURL() throws IOException, InterruptedException {
//		WebDriver driver = useHeadlessDriver();
		WebDriver driver = new ChromeDriver();

		LoginPageDesktop loginPageDesktop = new LoginPageDesktop(driver);
		loginPageDesktop.login();
		return loginPageDesktop.getCODE();
	}

	private static void logIfWrongStatusCode(Response response) {
		int statusCode = response.code();
		if (statusCode != 200) {
			logger.error("error while getting CloudID from server, status code: " + statusCode + " message: "
					+ response.message());
		}
	}

	public static WebDriver useHeadlessDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		return new ChromeDriver(options);
	}
}
