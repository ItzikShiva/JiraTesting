package jira.api.issue;

import jira.api.APIUtils;
import jira.api.issue.issuerequest.IssueRequest;
import jira.api.issue.issuerequest.Label;
import jira.api.issue.issuerequest.Update;
import jira.api.issue.getissueresponse.GetIssueResponse;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static jira.api.APIUtils.insertValuesForIssueRequest;
import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.*;

public class EditIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(EditIssueTests.class);

    public static String issueKeyToEdit = "JTP-29";

    @Test
    public static void editIssue() {
        apiService.login();

        Response response = issueService.getIssue(issueKeyToEdit);
        Assert.assertEquals(response.code(), 200);
        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals(issueKeyToEdit, getIssueResponse.getKey());
        logger.info("got issue with key: " + issueKeyToEdit);

        String summaryUpdate = "test summary from api testing-1";
        IssueRequest editIssueRequest = new IssueRequest();
        insertValuesForIssueRequest(editIssueRequest, true, summaryUpdate);
        response = issueService.editIssue(issueKeyToEdit, editIssueRequest);
        Assert.assertEquals(response.code(), 204);

        response = issueService.getIssue(issueKeyToEdit);
        Assert.assertEquals(response.code(), 200);
        getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals(summaryUpdate, getIssueResponse.getFields().getSummary());
        logger.info("issue with key: " + issueKeyToEdit + " was edited");
    }

    @Test
    public static void incorrectAuthentication() {
        Response response = issueService.editIssue(issueKeyToEdit, new IssueRequest(), INVALID_TOKEN);
        Assert.assertEquals(response.code(), 401);
        logger.info("authentication credentials are incorrect or missing");
    }

    @Test
    public static void editIssueWithInvalidIssueKey() {
        apiService.login();

        Response response = issueService.editIssue(INVALID_ISSUE_KEY, new IssueRequest());
        Assert.assertEquals(response.code(), 404);
        logger.info("invalid issue key");
    }

    @Test
    public static void userWithoutPermission() {
        apiService.login("read:me");
        Response response = issueService.editIssue(issueKeyToEdit, new IssueRequest());
        Assert.assertEquals(response.code(), 403);
        logger.info("the user does not have permission to view it");
    }

    @Test
    public static void requestBodyMissing() {
        apiService.login();

        IssueRequest editIssueRequest = new IssueRequest();
        APIUtils.insertValuesForIssueRequest(editIssueRequest, true);
        putInvalidLabel(editIssueRequest);

        Response response = issueService.editIssue(issueKeyToEdit, editIssueRequest);
        Assert.assertEquals(response.code(), 400);
        logger.info("the request body of edit issue with key: " + issueKeyToEdit + " is missing.");
    }

    public static void putInvalidLabel(IssueRequest editIssueRequest) {
        List<Label> labels = Arrays.asList(new Label(INVALID_LABEL));
        Update update = new Update();
        update.setLabels(labels);
        editIssueRequest.setUpdate(update);
    }
}


