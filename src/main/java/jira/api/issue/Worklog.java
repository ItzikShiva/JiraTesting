
package jira.api.issue;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Worklog {

    @SerializedName("startAt")
    @Expose
    private Integer startAt;
    @SerializedName("maxResults")
    @Expose
    private Integer maxResults;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("worklogs")
    @Expose
    private List<Object> worklogs;

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
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

    public List<Object> getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(List<Object> worklogs) {
        this.worklogs = worklogs;
    }

}
