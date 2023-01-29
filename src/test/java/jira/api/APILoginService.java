package jira.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class LoginService {
	public static String token;
	public static APILoginService apiLoginService = new APILoginService();

	public static void login() throws ClientProtocolException, IOException {
//login method - call to service! can return boolean if success?
		String code = getCode();
		String token = getToken(code);
	}

	// get code token and cloudID will call the service. also can use for loginTests
	public static String getCode() throws ClientProtocolException, IOException {
		apiLoginService.getCodeURL();
		return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2MjM2Zjc1Njg2NmI4MTAwNjllMjBkOWMiLCJuYmYiOjE2NzQ5OTE0MDUsImlzcyI6ImF1dGguYXRsYXNzaWFuLmNvbSIsImlhdCI6MTY3NDk5MTQwNSwiZXhwIjoxNjc0OTkxNzA1LCJhdWQiOiJFTWNaemF6bVJkcWRHbUQ0OHpqbUNEM3RWaWVsbXB3TiIsImp0aSI6IjYzZmVlZDFlLTNlNzItNDgxOC1hZmY0LWNkYWY4YTcxYmMyNSIsImNsaWVudF9hdXRoX3R5cGUiOiJQT1NUIiwiaHR0cHM6Ly9pZC5hdGxhc3NpYW4uY29tL3Nlc3Npb25faWQiOiJiZDE2NDViOS02ZmE0LTRmZWYtOGY5Mi1iNGVlOTE0ZDVhYjIiLCJodHRwczovL2lkLmF0bGFzc2lhbi5jb20vdmVyaWZpZWQiOnRydWUsImh0dHBzOi8vaWQuYXRsYXNzaWFuLmNvbS91anQiOiI2M2ZlZWQxZS0zZTcyLTQ4MTgtYWZmNC1jZGFmOGE3MWJjMjUiLCJzY29wZSI6WyJyZWFkOm1lIiwicmVhZDpqaXJhLXdvcmsiLCJyZWFkOmFjY291bnQiXSwiaHR0cHM6Ly9pZC5hdGxhc3NpYW4uY29tL2F0bF90b2tlbl90eXBlIjoiQVVUSF9DT0RFIn0.Mc9M8Ma1U7PhCxF7f9gp7eoVy7AuJk2Rb9THgfdbk3w#";
	}

	public static String getToken(String code) {
		return "";
	}

	public static String getCloudID() {
		return "";
	}

//logout method
	public static void logout() {

	}
}
