package jira.api.issue;

import java.io.IOException;

import jira.api.issue.getissueresponse.GetIssueResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.Response;

import static jira.api.APIUtils.insertValuesForBaseIssueRequest;
import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.*;


public class CreateIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(CreateIssueTests.class);


    @Test
    public static void createIssue() {
        apiService.login();
        // we not really use Metadata(next 2 lines) in that flow, just assert that we get it.
        Response response = issueService.getCreateMetadata();
        Assert.assertEquals(response.code(), 200);
        logger.info("got create issue Metadata");

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        insertValuesForBaseIssueRequest(createIssueRequest, true);
        response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 201);
        CreateIssueResponse createIssueResponse = responseToObject(response, CreateIssueResponse.class);
        String createdIssueKey = createIssueResponse.getKey();
        logger.info("Issue created with key: " + createdIssueKey);

        response = issueService.getIssue(createdIssueKey);
        Assert.assertEquals(response.code(), 200);
        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals(getIssueResponse.getKey(), createdIssueKey);
        logger.info("issue with key: " + createdIssueKey + " got successfully from server");

        response = issueService.deleteIssue(createdIssueKey);
        Assert.assertEquals(response.code(), 204);
        logger.info("issue with key: " + createdIssueKey + " Deleted successfully from server");
    }

    @Test
    public static void createIssueWithMissingFields() {
        apiService.login();
        // we not really use Metadata(next 2 lines) in that flow, just assert that we get it.
        Response response = issueService.getCreateMetadata();
        Assert.assertEquals(response.code(), 200);
        logger.info("got create issue Metadata");

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 400);
        logger.info("Issue was not created - problem in JSON request");
    }

    @Test
    public static void createIssueWithInvalidFieldValues() {
        apiService.login();
        // we not really use Metadata(next 2 lines) in that flow, just assert that we get it.
        Response response = issueService.getCreateMetadata();
        Assert.assertEquals(response.code(), 200);
        logger.info("got create issue Metadata");

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        insertValuesForBaseIssueRequest(createIssueRequest, false);
        response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 400);
        //TODO - ask, Hod there are different errors, depend which value is missing, so i print the obj response, ok?
        try {
            logger.info("Issue was not created - problem in: " + response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public static void incorrectAuthentication() {
        Response response = issueService.createIssue(new CreateIssueRequest(), INVALID_TOKEN);
        Assert.assertEquals(response.code(), 401);
        logger.info("authentication credentials are incorrect or missing");
    }

    @Test
    public static void userWithoutPermission() {
        apiService.login("read:me");
        Response response = issueService.createIssue(new CreateIssueRequest());
        Assert.assertEquals(response.code(), 403);
        logger.info("the user does not have permission to view it");
    }
}
