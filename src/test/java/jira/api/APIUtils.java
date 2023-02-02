package jira.api;

import static jira.api.APICommonUtils.gson;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIUtils {
	private static final Logger logger = LogManager.getLogger(APIUtils.class);

	public static <T> T responseToPOJO(Response response, Class<T> clazz) {
		  ResponseBody responseBody = response.body();
		  String jsonString = null;

		  try {
		    jsonString = responseBody.string();
		  } catch (IOException e) {
		    logger.error("error while parsing response body", e);
		  }

		  T getIssueResponse = gson.fromJson(jsonString, clazz);
		  return getIssueResponse;
		}
}