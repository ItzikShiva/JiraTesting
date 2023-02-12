package jira.api.issue;

import jira.api.issue.baseissuerequest.*;
import jira.api.issue.getissueresponse.GetIssueResponse;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static jira.api.APIUtils.responseToObject;
import static jira.api.issue.IssueConstants.*;

public class EditIssueTests extends BaseIssueTests {
    private static final Logger logger = LogManager.getLogger(EditIssueTests.class);

    @Test
    public static void editIssue() {
        apiService.login();
        //create metadata - ask Hod if it needs for the test - actually I think not. because I don't test the "create metadata"?

        Response response = issueService.getIssue("JTP-29");
        Assert.assertEquals(response.code(), 200);
        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals("JTP-29", getIssueResponse.getKey());
        logger.info("got issue with id: " + "JTP-29");

        EditIssueRequest editIssueRequest = new EditIssueRequest();
        insertValuesForEditIssue(editIssueRequest, true);
        response = issueService.editIssue("JTP-29", editIssueRequest);
        Assert.assertEquals(response.code(), 204);


        response = issueService.getIssue("JTP-29");
        Assert.assertEquals(response.code(), 200);
        getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals("11from Test - for testing - itzikTest", getIssueResponse.getFields().getSummary());
        logger.info("issue with key: " + "JTP-29" + " was edited");

    }


    /**
     * this method insert values for CreateIssueRequest,the second parameter - true for valid values, false for invalid values
     */
    public static void insertValuesForEditIssue(EditIssueRequest editIssueRequest, boolean validValues) {
        Fields fields = new Fields();
        fields.setSummary("11from Test - for testing - itzikTest");
        if (validValues) {
            fields.setIssuetype(new Issuetype(ISSUE_TYPE));
        } else {
            fields.setIssuetype(new Issuetype("10070"));
        }
        fields.setProject(new Project(PROJECT_ID));
        fields.setCustomfield_10020(CUSTOM_FIELD_10020_ID);
        fields.setReporter(new Reporter(REPORTER_ID));
        fields.setLabels(LABELS);
        fields.setAssignee(new Assignee(ASSIGNEE_ID));
        editIssueRequest.setFields(fields);
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


