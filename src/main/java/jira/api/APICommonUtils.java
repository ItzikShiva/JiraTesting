package jira.api;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class APICommonUtils {
	public static Gson gson = new Gson();
	public static OkHttpClient client = new OkHttpClient();

}
