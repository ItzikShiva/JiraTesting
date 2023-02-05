package jira.api.issue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import jira.api.APIService;
import jira.api.issue.getissueresponse.GetIssueResponse;

import static jira.api.APIUtils.*;

import okhttp3.Response;

public class GetIssueTests {
	private static final Logger logger = LogManager.getLogger(GetIssueTests.class);

	public static APIService apiService = new APIService();
	public static IssueService issueService = new IssueService();

	public static String validIssueId = "JTP-1";
	public static String invalidToken = "Bearer eyJraWQiOiJmZTM2ZThkMzZjMTS2N2RjYTgyNTg5MmEiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI5Y2QyZjlmMS0zYzM0LTQ1NDktOTg2ZC0xZGJiZTA1YjYzNjEiLCJzdWIiOiI2MjM2Zjc1Njg2NmI4MTAwNjllMjBkOWMiLCJuYmYiOjE2NzUzMzg0NDMsImlzcyI6Imh0dHBzOi8vYXRsYXNzaWFuLWFjY291bnQtcHJvZC5wdXMyLmF1dGgwLmNvbS8iLCJpYXQiOjE2NzUzMzg0NDMsImV4cCI6MTY3NTM0MjA0MywiYXVkIjoiRU1jWnphem1SZHFkR21ENDh6am1DRDN0VmllbG1wd04iLCJodHRwczovL2lkLmF0bGFzc2lhbi5jb20vYXRsX3Rva2VuX3R5cGUiOiJBQ0NFU1MiLCJodHRwczovL2lkLmF0bGFzc2lhbi5jb20vc2Vzc2lvbl9pZCI6IjE2ZGNkNWYxLTRkNTktNDczZS05Y2QwLTdmMjdjNjI5NTVjMyIsImh0dHBzOi8vYXRsYXNzaWFuLmNvbS9maXJzdFBhcnR5IjpmYWxzZSwiaHR0cHM6Ly9hdGxhc3NpYW4uY29tL29hdXRoQ2xpZW50SWQiOiJFTWNaemF6bVJkcWRHbUQ0OHpqbUNEM3RWaWVsbXB3TiIsImh0dHBzOi8vYXRsYXNzaWFuLmNvbS92ZXJpZmllZCI6dHJ1ZSwiY2xpZW50X2F1dGhfdHlwZSI6IlBPU1QiLCJodHRwczovL2F0bGFzc2lhbi5jb20vZW1haWxEb21haW4iOiJnbWFpbC5jb20iLCJodHRwczovL2lkLmF0bGFzc2lhbi5jb20vdWp0IjoiZGM5MDEzNjUtZmNlYi00YzFhLTliY2EtNzExZDRjZmUwMWYwIiwiaHR0cHM6Ly9hdGxhc3NpYW4uY29tLzNsbyI6dHJ1ZSwiaHR0cHM6Ly9hdGxhc3NpYW4uY29tL3N5c3RlbUFjY291bnRJZCI6IjYzZDYzOGY3ODJlZWVlNzhhNGNkMDZjZSIsImh0dHBzOi8vaWQuYXRsYXNzaWFuLmNvbS92ZXJpZmllZCI6dHJ1ZSwiY2xpZW50X2lkIjoiRU1jWnphem1SZHFkR21ENDh6am1DRDN0VmllbG1wd04iLCJzY29wZSI6InJlYWQ6amlyYS13b3JrIHJlYWQ6YWNjb3VudCByZWFkOm1lIiwiaHR0cHM6Ly9hdGxhc3NpYW4uY29tL3N5c3RlbUFjY291bnRFbWFpbERvbWFpbiI6ImNvbm5lY3QuYXRsYXNzaWFuLmNvbSIsImh0dHBzOi8vYXRsYXNzaWFuLmNvbS9zeXN0ZW1BY2NvdW50RW1haWwiOiIwZDQ5NjNlMy01MTEzLTQ4MzYtOWRmNS05MTYzMjA4YTY1MTZAY29ubmVjdC5hdGxhc3NpYW4uY29tIn0.ujfSG91wC2mn3OO_VZbby3LoPbdLM-jM35Jj5RRA4zJ1WTUbJOqRUGtZ4yeTD3rJ46Xxj2_b8imUVHtEUGA4XKg73UcqYIG5g8E2MrF5-4g8yT0XqRIleGrZFJjr2OUaLN9BQUNbOLOQU0Z1EvebxUkjIl0o-kI_lL28QDnAFWfFt6EnHHRMhXYaCjLumUd0zAjuwl-xyoaf3H9gjVDdXobPX1YjXnUOfUZYKFTynbd4OUAShgXV3gVgHiYsHzBO9K0CPbU11eG0KQGanGjFPtJgfu1bTRa8f6d5pON4u6_cJ7xuQxhE0wiUdq9G-8dEkvNo0Wk_S2B0p3m-pKL--g";

	@Test
	public static void getIssue() {
		apiService.login();
		Response response = issueService.getIssue(validIssueId);
		Assert.assertTrue(response.code() == 200);
		GetIssueResponse getIssueResponse = responseToPOJO(response, GetIssueResponse.class);
		Assert.assertEquals(validIssueId, getIssueResponse.getKey());
		logger.info("got issue with id: " + validIssueId);
	}

	@Test
	public static void invalidIssueId() {
		apiService.login();
		String id = "P-1";
		Response response = issueService.getIssue(id);
		Assert.assertTrue(response.code() == 404);
		logger.info("invalid issue id");
	}

	@Test
	public static void incorrectAuthentication() {
		Response response = issueService.getIssue(validIssueId, invalidToken);
		Assert.assertTrue(response.code() == 401);
		logger.info("authentication credentials are incorrect or missing");
	}

	@Test
	public static void userWithoutPermission() {
		apiService.login("read:me");
		Response response = issueService.getIssue(validIssueId);
		Assert.assertTrue(response.code() == 404);
		logger.info("the user does not have permission to view it");
	}
}
