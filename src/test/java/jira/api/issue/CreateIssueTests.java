package jira.api.issue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jira.api.issue.getissueresponse.GetIssueResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import jira.api.issue.createissuerequest.Assignee;
import jira.api.issue.createissuerequest.Content;
import jira.api.issue.createissuerequest.Content__1;
import jira.api.issue.createissuerequest.CreateIssueRequest;
import jira.api.issue.createissuerequest.Description;
import jira.api.issue.createissuerequest.Fields;
import jira.api.issue.createissuerequest.Issuetype;
import jira.api.issue.createissuerequest.Project;
import jira.api.issue.createissuerequest.Reporter;
import okhttp3.Response;

import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.*;


public class CreateIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(CreateIssueTests.class);

    private static String createdIssueKey;

    @Test
    public static void createIssue() {
        apiService.login();

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        insertValuesForCreateIssue(createIssueRequest, true);
        Response response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 201);
        CreateIssueResponse createIssueResponse = responseToObject(response, CreateIssueResponse.class);
        createdIssueKey = createIssueResponse.getKey();
        logger.info("Issue created with key: " + createdIssueKey);

        response = issueService.getIssue(createdIssueKey);
        Assert.assertEquals(response.code(), 200);
        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals(getIssueResponse.getKey(), createdIssueKey);
        logger.info("issue with key: " + createdIssueKey + " got successfully from server");

    }

    @Test
    public static void createIssueWithMissingFields() {
        apiService.login();

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        Response response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 400);
        logger.info("Issue was not created - problem in JSON request");
    }

    @Test
    public static void createIssueWithInvalidIssueType() {
        apiService.login();

        CreateIssueRequest createIssueRequest = new CreateIssueRequest();
        insertValuesForCreateIssue(createIssueRequest, false);
        Response response = issueService.createIssue(createIssueRequest);
        Assert.assertEquals(response.code(), 400);

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

    /**
     * cleanup() delete the issue that created in createIssue();
     */
    @AfterClass
    public static void cleanup(){
        apiService.login();

        Response response = issueService.deleteIssue(createdIssueKey);
        Assert.assertEquals(response.code(), 204);
        logger.info("issue with key: " + createdIssueKey + " Deleted successfully from server");
    }

    /**
     * this method insert values for CreateIssueRequest,the second parameter - true for valid values, false for invalid values
     */
    public static void insertValuesForCreateIssue(CreateIssueRequest createIssueRequest, boolean validIssueType) {
        Fields fields = new Fields();
        fields.setSummary(SUMMARY);
        if (validIssueType) {
            fields.setIssuetype(new Issuetype(ISSUE_TYPE));
        } else {
            fields.setIssuetype(new Issuetype("10070"));
        }
        fields.setProject(new Project(PROJECT_ID));
        fields.setCustomfield_10020(CUSTOM_FIELD_10020_ID);
        fields.setReporter(new Reporter(REPORTER_ID));
        fields.setLabels(LABELS);
        fields.setAssignee(new Assignee(ASSIGNEE_ID));
        createIssueRequest.setFields(fields);
        Description description = new Description();
        description.setType(DESCRIPTION_TYPE);
        description.setVersion(DESCRIPTION_VERSION);
        fields.setDescription(description);
        List<Content> content = Arrays.asList(new Content(DESCRIPTION_CONTENT_TYPE));
        description.setContent(content);
        List<Content__1> contents__1 = Arrays.asList(new Content__1(DESCRIPTION_CONTENT__1_TEXT, DESCRIPTION_CONTENT__1_TYPE));
        content.get(0).setContent(contents__1);
    }
}
