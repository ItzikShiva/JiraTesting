package jira.api.login;

import java.util.ArrayList;

public class Cloud {
	public String id;
	public String url;
	public String name;
	public ArrayList<String> scopes;
	public String avatarUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getScopes() {
		return scopes;
	}

	public void setScopes(ArrayList<String> scopes) {
		this.scopes = scopes;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
