package jira.api.issue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jira.api.APIService;
import static jira.api.APIUtils.*;

import okhttp3.Response;

public class GetIssueTests {
	private static final Logger logger = LogManager.getLogger(GetIssueTests.class);

	public static APIService apiService = new APIService();
	public static IssueService issueService = new IssueService();

	//TODO - ask, i can do beforeSuite then it will run before all the suites instead of running before every test file. what to do?
	@BeforeClass
	public static void login() {
		apiService.login();
		logger.info("for development only: " + apiService.token);
	}

	@Test
	public static void getIssue() {
		String id = "JTP-1";
		Response response = issueService.getIssue(id);
		Assert.assertTrue(response.code() == 200);
		GetIssueResponse getIssueResponse = responseToPOJO(response, GetIssueResponse.class);
		Assert.assertEquals(id, getIssueResponse.getKey());
	}
	//TODO - ask - i can exclude from testng.xml. but should i? because i dont need the login for this

	@Test
	public static void wrongIssueId() {
		String id = "P-1";
		Response response = issueService.getIssue(id);
		Assert.assertTrue(response.code() == 404);
//		GetIssueResponse getIssueResponse = responseToPOJO(response, GetIssueResponse.class);
//		Assert.assertEquals(id, getIssueResponse.getKey());
	}
}
