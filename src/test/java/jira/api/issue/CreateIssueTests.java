package jira.api.issue;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import jira.api.APIService;
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

public class CreateIssueTests {
	private static final Logger logger = LogManager.getLogger(CreateIssueTests.class);

	public static APIService apiService = new APIService();
	public static IssueService issueService = new IssueService();

	/*
	 * TODO - itzik
	 * 1. take all to func.
	 * 2. make constant Class
	 * 3. make response
	 */
	
	
	@Test
	public static void createIssue() {
		apiService.login();
		// we not use it in that flow, just assert that we get it
		Response metadataResponse = issueService.getCreateMetadata();
		Assert.assertEquals(metadataResponse.code(), 200);
		logger.info("got create issue Metadata");
		CreateIssueRequest createIssueRequest = new CreateIssueRequest();
		insertValuesForCreateIssue(createIssueRequest);
		logger.info(createIssueRequest);


//		Response response = issueService.createIssue(request);

//		new Content__1();
//		make it createIssueResponse class

	}

	public static void insertValuesForCreateIssue(CreateIssueRequest createIssueRequest){
		Fields fields = new Fields();
		fields.setSummary("summary - itzikTest");

		fields.setIssuetype(new Issuetype("10005"));
		fields.setProject(new Project("10005"));
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
//		return createIssueRequest;
	}
}
