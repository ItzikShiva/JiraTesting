
package jira.api.issue.issuerequest;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Fields {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("customfield_10020")
    @Expose
    private int customfield_10020;
    @SerializedName("issuetype")
    @Expose
    private Issuetype issuetype;
    @SerializedName("project")
    @Expose
    private Project project;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("reporter")
    @Expose
    private Reporter reporter;
    @SerializedName("labels")
    @Expose
    private List<String> labels;
    @SerializedName("assignee")
    @Expose
    private Assignee assignee;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public int getCustomfield_10020() {
        return customfield_10020;
    }

    public void setCustomfield_10020(int customfield_10020) {
        this.customfield_10020 = customfield_10020;
    }
    
    public Issuetype getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(Issuetype issuetype) {
        this.issuetype = issuetype;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

}
