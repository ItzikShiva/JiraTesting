
package jira.api.issue.createissuerequest;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Issuetype {

    @SerializedName("id")
    @Expose
    private String id;

    public Issuetype(String id) {
		super();
		this.id = id;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
