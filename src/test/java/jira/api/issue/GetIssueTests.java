package jira.api.issue;

import jira.api.issue.getissueresponse.GetIssueResponse;
import jira.nonCriticalListener.NonCritical;
import jira.nonCriticalListener.NonCriticalListener;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.*;

@Listeners(NonCriticalListener.class)
public class GetIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(GetIssueTests.class);


    @NonCritical(bugKey = CLOSED_BUG_KEY)
    @Test
    public static void getIssue() {
        apiService.login();
        Response response = issueService.getIssue(VALID_ISSUE_KEY);
        Assert.assertEquals(response.code(), 200);
        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals(VALID_ISSUE_KEY, getIssueResponse.getKey());
        logger.info("got issue with id: " + VALID_ISSUE_KEY);
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
        Response response = issueService.getIssue(VALID_ISSUE_KEY, INVALID_TOKEN);
        Assert.assertEquals(response.code(), 401);
        logger.info("authentication credentials are incorrect or missing");
    }

    //need to open bug for this
    @Test
    public static void userWithoutPermission() {
        apiService.login("read:me");
        Response response = issueService.getIssue(VALID_ISSUE_KEY);
        Assert.assertEquals(response.code(), 404);
        logger.info("the user does not have permission to view it");
    }
}
