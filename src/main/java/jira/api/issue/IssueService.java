package jira.api.issue;

import static jira.api.APICommonUtils.*;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jira.api.APIService;
import okhttp3.Request;
import okhttp3.Response;

public class IssueService {
    private static final Logger logger = LogManager.getLogger(IssueService.class);
    private static APIService apiService = new APIService();
    private static String baseUrl = "https://api.atlassian.com/ex/jira/93916ef5-a97b-47de-9a28-80fe8572a67e/rest/api/3/issue/";
    public static final MediaType jsonMediaType = MediaType.parse("application/json");

    /**
     * editIssue()
     * param issueKey - required
     * param editIssueRequest - required
     * param token - optional. if not provide, uses default - valid token.
     */
    public Response editIssue(String issueKey, EditIssueRequest editIssueRequest) {
        return editIssue(issueKey, editIssueRequest, apiService.token);
    }

    public Response editIssue(String issueKey, EditIssueRequest editIssueRequest, String token) {
        logger.info("sending request for edit issue, with issueKey: " + issueKey + " to server");

        RequestBody body = RequestBody.create(gson.toJson(editIssueRequest), jsonMediaType);
        Request request = new Request.Builder().url(baseUrl + issueKey).addHeader("Accept", "application/json")
                .addHeader("Authorization", token).put(body).build();

        return executeMethod(request, logger);
    }

    public Response deleteIssue(String issueKey) {
        logger.info("sending request for delete issue, with issueKey: " + issueKey + " to server");

        Request request = new Request.Builder().url(baseUrl + issueKey).addHeader("Authorization", apiService.token)
                .delete().build();

        return executeMethod(request, logger);
    }

    /**
     * createIssue()
     * param createIssueRequest - required
     * param token - optional. if not provide, uses default - valid token.
     */
    public Response createIssue(CreateIssueRequest createIssueRequest) {
        return createIssue(createIssueRequest, apiService.token);
    }

    public Response createIssue(CreateIssueRequest createIssueRequest, String token) {
        logger.info("sending request for create issue to server");

        RequestBody body = RequestBody.create(gson.toJson(createIssueRequest), jsonMediaType);
        Request request = new Request.Builder().url(baseUrl).addHeader("Accept", "application/json")
                .addHeader("Authorization", token).post(body).build();

        return executeMethod(request, logger);
    }

    public Response getCreateMetadata() {
        logger.info("getting create issue metadata from server");

        Request request = new Request.Builder().url(baseUrl + "createmeta").addHeader("Accept", "application/json")
                .addHeader("Authorization", apiService.token).build();

        return executeMethod(request, logger);
    }

    /**
     * getIssue()
     * param issueKey - required
     * param token - optional. if not provide, uses default - valid token.
     */
    public Response getIssue(String issueKey) {
        return getIssue(issueKey, apiService.token);
    }

    public Response getIssue(String issueKey, String token) {
        logger.info("getting Issue from server");

        Request request = new Request.Builder().url(baseUrl + issueKey).addHeader("Accept", "application/json")
                .addHeader("Authorization", token).build();

        return executeMethod(request, logger);
    }
}
