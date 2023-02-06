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

public class CreateIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(CreateIssueTests.class);


    /*
    * ask - this test is 4 steps, and it a bit long. but i can't see option for shorter. what do you say?
    * another question - i initialize one time "response", and then assign to it again and again, is it ok?
    */
    @Test

    public static void createIssue() {
        apiService.login();
        // we not use it in that flow, just assert that we get it
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


    //TODO - ask, Hod, should i put constant? the values are only here (at least for now..)
    public static void insertValuesForCreateIssue(CreateIssueRequest createIssueRequest) {
        Fields fields = new Fields();
        fields.setSummary("summary - itzikTest");
        fields.setIssuetype(new Issuetype("10005"));
        fields.setProject(new Project("10001"));
        fields.setCustomfield_10020(2);
        fields.setReporter(new Reporter("6236f756866b810069e20d9c"));
        fields.setLabels(Arrays.asList("bugfix", "blitz_test"));
        fields.setAssignee(new Assignee("6236f756866b810069e20d9c"));
        createIssueRequest.setFields(fields);
        Description description = new Description();
        description.setType("doc");
        description.setVersion(1);
        fields.setDescription(description);
        List<Content> content = Arrays.asList(new Content("paragraph"));
        description.setContent(content);
        List<Content__1> contents__1 = Arrays.asList(new Content__1("description fields - itzikTest", "text"));
        content.get(0).setContent(contents__1);
    }
}
