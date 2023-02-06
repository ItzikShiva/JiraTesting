package jira.api.issue;

import java.util.Arrays;
import java.util.List;

import jira.api.issue.getissueresponse.GetIssueResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
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
import static jira.api.issue.CreateIssueConstants.*;


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
        insertValuesForCreateIssue(createIssueRequest);
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


    public static void insertValuesForCreateIssue(CreateIssueRequest createIssueRequest) {
        Fields fields = new Fields();
        fields.setSummary(SUMMARY);
        fields.setIssuetype(new Issuetype(ISSUE_TYPE));
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
