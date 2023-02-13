
package jira.api.issue.issuerequest;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Content {
	@SerializedName("type")
    @Expose
    private String type;
    @SerializedName("content")
    @Expose
    private List<Content__1> content;

    public Content(String type) {
		super();
		this.type = type;
	}
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Content__1> getContent() {
        return content;
    }

    public void setContent(List<Content__1> content) {
        this.content = content;
    }

}
