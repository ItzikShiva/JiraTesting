package jira.api.issue;

import jira.api.issue.getissueresponse.GetIssueResponse;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static jira.api.APIUtils.insertValuesForBaseIssueRequest;
import static jira.api.APIUtils.responseToObject;

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
        insertValuesForBaseIssueRequest(editIssueRequest, true, "test summary from api testing");
        response = issueService.editIssue("JTP-29", editIssueRequest);
        Assert.assertEquals(response.code(), 204);


        response = issueService.getIssue("JTP-29");
        Assert.assertEquals(response.code(), 200);
        getIssueResponse = responseToObject(response, GetIssueResponse.class);
        Assert.assertEquals("test summary from api testing", getIssueResponse.getFields().getSummary());
        logger.info("issue with key: " + "JTP-29" + " was edited");

    }


}


