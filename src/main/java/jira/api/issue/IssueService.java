package jira.api.issue;

import static jira.api.APICommonUtils.client;
import static jira.api.APICommonUtils.gson;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static jira.api.APIService.token;
import okhttp3.Request;
import okhttp3.Response;

public class IssueService {
	private static final Logger logger = LogManager.getLogger(IssueService.class);
//	private static APIService apiService = new APIService();

	public Response getIssue(String id) {
		logger.info("getting Issue from server");
		String getIssueUrl = "https://api.atlassian.com/ex/jira/93916ef5-a97b-47de-9a28-80fe8572a67e/rest/api/3/issue/"
				+ id;

		Request request = new Request.Builder().url(getIssueUrl).addHeader("Accept", "application/json")
				.addHeader("Authorization", token).build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			logger.info("got response from server");
		} catch (IOException e) {
			logger.error("error in getting response from server", e);
		}
		return response;
	}

}
