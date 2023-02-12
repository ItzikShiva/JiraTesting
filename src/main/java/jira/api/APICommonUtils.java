package jira.api;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class APICommonUtils {
    public static Gson gson = new Gson();
    public static OkHttpClient client = new OkHttpClient();

    /**
     * this method execute http request with okHttp client
     * get request and logger as parameter
     * execute the request, log and return response as Response type
     **/
    public static Response executeMethod(Request request, Logger logger) {
        logger.info("Performing " + request.method() + " request to: " + request.url());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            logger.info("got response from server");
        } catch (IOException e) {
            logger.error("error in getting response from server", e);
        }
        return response;
    }
}
