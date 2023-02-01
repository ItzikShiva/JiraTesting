package jira.api;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonSyntaxException;

import jira.api.login.GetAccessTokenRequest;
import jira.api.login.GetAccessTokenResponse;
import jira.api.login.GetCloudResponse;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import static jira.api.APICommonUtils.*;
import static jira.ui.login.UILoginService.getSecretCode;

public class APIService {
	private static final Logger logger = LogManager.getLogger(APIService.class);

	// stackoverflow and postman using this kind, (i just remove the utf) and
	// changed the name
	public static final MediaType jsonMediaType = MediaType.parse("application/json");

	private static String code;
	private static String token;
	private static String cloudId;

	public void login() {
		// TODO - ask, code = getSecretCode(); calls to UI, but i can't think about
		// better place for it for this case
		code = getSecretCode();
		token = getAccessToken(code);
		cloudId = getCloudID(token);
	}

	public static String getAccessToken(String code) {
		logger.info("getting TOKEN from server");
		String baseTokenUrl = "https://auth.atlassian.com/oauth/token";
		GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest(code);
		RequestBody body = RequestBody.create(gson.toJson(getAccessTokenRequest), jsonMediaType);
		Request request = new Request.Builder().url(baseTokenUrl).post(body).build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.code() == 200) {
				logger.info("got TOKEN response from server");
			}
		} catch (IOException e) {
			logger.error("error in getting token from server", e);
		}
		return "Bearer " + getTokenFromResponse(response);
	}

	public static String getCloudID(String token) {
		logger.info("getting Cloud-ID from server");
		String baseCloudUrl = "https://api.atlassian.com/oauth/token/accessible-resources";

		Request request = new Request.Builder().url(baseCloudUrl).addHeader("Authorization", token)
				.addHeader("Accept", "application/json").build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.code() == 200) {
				logger.info("got Cloud-Id from server");
			}
		} catch (IOException e) {
			logger.error("error in getting Cloud-Id from server", e);
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

		GetCloudResponse getCloudResponse = gson.fromJson(jsonString, GetCloudResponse.class);
		return getCloudResponse.getId();
	}

	private static String getTokenFromResponse(Response response) {
		ResponseBody responseBody = response.body();
		GetAccessTokenResponse getAccessTokenResponse = null;
		try {
			getAccessTokenResponse = gson.fromJson(responseBody.string(), GetAccessTokenResponse.class);
		} catch (JsonSyntaxException | IOException e) {
			logger.error("problem while getting Token from response", e);
		}
		return getAccessTokenResponse.getAccess_token();
	}
}
