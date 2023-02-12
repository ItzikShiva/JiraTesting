package jira.api;

import static jira.api.APICommonUtils.gson;
import static jira.api.issue.IssueConstants.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jira.api.issue.baseissuerequest.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIUtils {
    private static final Logger logger = LogManager.getLogger(APIUtils.class);

    public static <T> T responseToObject(Response response, Class<T> clazz) {
        ResponseBody responseBody = response.body();
        String jsonString = null;

        try {
            jsonString = responseBody.string();
        } catch (IOException e) {
            logger.error("error while parsing response body", e);
        }

        T responseObject = gson.fromJson(jsonString, clazz);
        return responseObject;
    }

    /**
     * Hod - i wasn't sure where this func should be? i think to create new util("smaller" then APIUtils) IssueUtils
     * <p>
     * this method insert values for CreateIssueRequest,the second parameter - true for valid values, false for invalid values;
     * third parameter - optional - summary
     * (use by createIssueTests and EditIssueTests)
     */
    public static void insertValuesForBaseIssueRequest(BaseIssueRequest baseIssueRequest, boolean validValues) {
        insertValuesForBaseIssueRequest(baseIssueRequest, validValues, SUMMARY);
    }

    public static void insertValuesForBaseIssueRequest(BaseIssueRequest baseIssueRequest, boolean validValues, String summary) {
        Fields fields = new Fields();
        fields.setSummary(summary);
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
        baseIssueRequest.setFields(fields);
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
