
package jira.api.issue;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Comment {

    @SerializedName("comments")
    @Expose
    private List<Object> comments;
    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("maxResults")
    @Expose
    private Integer maxResults;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("startAt")
    @Expose
    private Integer startAt;

    public List<Object> getComments() {
        return comments;
    }

    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

}
