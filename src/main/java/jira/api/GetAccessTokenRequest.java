package jira.api;

public class GetAccessTokenRequest {
	private String grant_type = "authorization_code";
	private String client_id = "EMcZzazmRdqdGmD48zjmCD3tVielmpwN";
	private String client_secret = "ATOAGP08RpVfirid_8ZpNWXQagGIMv_cMrGITtoODjN5GIcDlbXL-jmsZOmeUDV7wbTkC73F4623";
	private String code;
	private String redirect_uri = "https://task-day.onrender.com/";

	public GetAccessTokenRequest(String code) {
		super();
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

}
