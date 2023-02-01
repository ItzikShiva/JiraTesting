package jira.api.issue;

import org.testng.annotations.Test;

import jira.api.APIService;

public class IssueTests {
	public static APIService apiService = new APIService();
	
	@Test
	public static void login() {
		apiService.login();
	}


}
