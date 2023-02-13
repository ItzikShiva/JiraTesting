package jira.api.issue;

import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;

import static jira.api.APIUtils.insertValuesForBaseIssueRequest;
import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.INVALID_ISSUE_KEY;
import static jira.api.issue.IssueConstants.INVALID_TOKEN;

public class DeleteIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(DeleteIssueTests.class);
    public static final String issueKeyToDelete = "JTP-29";
    public static final String issueKeyToDeleteWithSubtasks = "JTP-40";

    @Test
    public static void deleteIssue() {
        apiService.login();

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        insertValuesForBaseIssueRequest(createIssueRequest, true);
        Response response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 201);
        CreateIssueResponse createIssueResponse = responseToObject(response, CreateIssueResponse.class);
        String issueKey = createIssueResponse.getKey();
        logger.info("issue was created with key: " + issueKey);

        response = issueService.deleteIssue(issueKey);
        Assert.assertEquals(response.code(), 204);
        logger.info("issue with key: " + issueKey + " deleted successfully");
    }

    @Test
    public static void incorrectAuthentication() {
        Response response = issueService.deleteIssue(issueKeyToDelete, INVALID_TOKEN);
        Assert.assertEquals(response.code(), 401);
        logger.info("issue wasn't delete - authentication credentials are incorrect or missing");
    }

    @Test
    public static void deleteIssueWithInvalidIssueKey() {
        apiService.login();

        Response response = issueService.deleteIssue(INVALID_ISSUE_KEY);
        Assert.assertEquals(response.code(), 404);
        logger.info("issue wasn't delete - invalid issue key");
    }

    @Test
    public static void userWithoutPermission() {
        apiService.login("read:me");

        Response response = issueService.deleteIssue(issueKeyToDelete);
        Assert.assertEquals(response.code(), 403);
        logger.info("issue wasn't delete - the user does not have permission to it");
    }

    @Test
    public static void deleteIssueWithSubtasks() {
        apiService.login();

        Response response = issueService.deleteIssue(issueKeyToDeleteWithSubtasks);
        Assert.assertEquals(response.code(), 400);
        logger.info("issue with key: " + issueKeyToDeleteWithSubtasks + " wasn't delete - the issue" +
                " has subtasks and \"deleteSubtasks\" is not set to true.");
    }
}
