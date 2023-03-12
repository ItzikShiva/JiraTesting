package jira.api.issue;

import jira.api.issue.getissueresponse.GetIssueResponse;
import jira.nonCritical.NonCritical;
import jira.nonCritical.NonCriticalListener;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.INVALID_ISSUE_KEY;
import static jira.api.issue.IssueConstants.INVALID_TOKEN;

@Listeners(NonCriticalListener.class)
public class GetIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(GetIssueTests.class);
    public static String validIssueKey = "JTP-1";

    //TES-2 - closed bug
    //TES-3 open bug
    @NonCritical(bugKey ="TES-2")
    @Test
    public static void getBugFailTest(){
//        String bugKey = "TES-2";

        apiService.login();
        Assert.assertEquals(0,0);

    }

    @Test
    public static void getIssue() {
        apiService.login();
        Response response = issueService.getIssue(validIssueKey);
        Assert.assertEquals(response.code(), 200);
        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals(validIssueKey, getIssueResponse.getKey());
        logger.info("got issue with id: " + validIssueKey);
    }

    @Test
    public static void invalidIssueKey() {
        apiService.login();

        Response response = issueService.getIssue(INVALID_ISSUE_KEY);
        Assert.assertEquals(response.code(), 404);
        logger.info("invalid issue key");
    }

    @Test
    public static void incorrectAuthentication() {
        Response response = issueService.getIssue(validIssueKey, INVALID_TOKEN);
        Assert.assertEquals(response.code(), 401);
        logger.info("authentication credentials are incorrect or missing");
    }

    //need to open bug for this
    @Test
    public static void userWithoutPermission() {
        apiService.login("read:me");
        Response response = issueService.getIssue(validIssueKey);
        Assert.assertEquals(response.code(), 404);
        logger.info("the user does not have permission to view it");
    }
}
