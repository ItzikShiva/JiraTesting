
package jira.api.issue.getissueresponse;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Fields {

    @SerializedName("statuscategorychangedate")
    @Expose
    private String statuscategorychangedate;
    @SerializedName("issuetype")
    @Expose
    private Issuetype issuetype;
    @SerializedName("timespent")
    @Expose
    private Object timespent;
    @SerializedName("customfield_10030")
    @Expose
    private Object customfield10030;
    @SerializedName("customfield_10031")
    @Expose
    private Object customfield10031;
    @SerializedName("project")
    @Expose
    private Project project;
    @SerializedName("fixVersions")
    @Expose
    private List<Object> fixVersions;
    @SerializedName("aggregatetimespent")
    @Expose
    private Object aggregatetimespent;
    @SerializedName("resolution")
    @Expose
    private Object resolution;
    @SerializedName("customfield_10027")
    @Expose
    private Object customfield10027;
    @SerializedName("customfield_10028")
    @Expose
    private Object customfield10028;
    @SerializedName("customfield_10029")
    @Expose
    private Object customfield10029;
    @SerializedName("resolutiondate")
    @Expose
    private Object resolutiondate;
    @SerializedName("workratio")
    @Expose
    private Integer workratio;
    @SerializedName("lastViewed")
    @Expose
    private String lastViewed;
    @SerializedName("watches")
    @Expose
    private Watches watches;
    @SerializedName("issuerestriction")
    @Expose
    private Issuerestriction issuerestriction;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("customfield_10020")
    @Expose
    private List<Customfield10020> customfield10020;
    @SerializedName("customfield_10021")
    @Expose
    private Object customfield10021;
    @SerializedName("customfield_10022")
    @Expose
    private Object customfield10022;
    @SerializedName("customfield_10023")
    @Expose
    private Object customfield10023;
    @SerializedName("priority")
    @Expose
    private Priority priority;
    @SerializedName("customfield_10024")
    @Expose
    private Object customfield10024;
    @SerializedName("customfield_10025")
    @Expose
    private Object customfield10025;
    @SerializedName("customfield_10026")
    @Expose
    private Object customfield10026;
    @SerializedName("labels")
    @Expose
    private List<Object> labels;
    @SerializedName("customfield_10016")
    @Expose
    private Object customfield10016;
    @SerializedName("customfield_10017")
    @Expose
    private Object customfield10017;
    @SerializedName("customfield_10018")
    @Expose
    private Customfield10018 customfield10018;
    @SerializedName("customfield_10019")
    @Expose
    private String customfield10019;
    @SerializedName("aggregatetimeoriginalestimate")
    @Expose
    private Object aggregatetimeoriginalestimate;
    @SerializedName("timeestimate")
    @Expose
    private Object timeestimate;
    @SerializedName("versions")
    @Expose
    private List<Object> versions;
    @SerializedName("issuelinks")
    @Expose
    private List<Object> issuelinks;
    @SerializedName("assignee")
    @Expose
    private Object assignee;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("components")
    @Expose
    private List<Object> components;
    @SerializedName("timeoriginalestimate")
    @Expose
    private Object timeoriginalestimate;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("customfield_10010")
    @Expose
    private Object customfield10010;
    @SerializedName("customfield_10014")
    @Expose
    private Object customfield10014;
    @SerializedName("timetracking")
    @Expose
    private Timetracking timetracking;
    @SerializedName("customfield_10015")
    @Expose
    private Object customfield10015;
    @SerializedName("customfield_10005")
    @Expose
    private Object customfield10005;
    @SerializedName("customfield_10006")
    @Expose
    private Object customfield10006;
    @SerializedName("security")
    @Expose
    private Object security;
    @SerializedName("customfield_10007")
    @Expose
    private Object customfield10007;
    @SerializedName("customfield_10008")
    @Expose
    private Object customfield10008;
    @SerializedName("customfield_10009")
    @Expose
    private Object customfield10009;
    @SerializedName("aggregatetimeestimate")
    @Expose
    private Object aggregatetimeestimate;
    @SerializedName("attachment")
    @Expose
    private List<Object> attachment;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("subtasks")
    @Expose
    private List<Object> subtasks;
    @SerializedName("reporter")
    @Expose
    private Reporter reporter;
    @SerializedName("aggregateprogress")
    @Expose
    private Aggregateprogress aggregateprogress;
    @SerializedName("customfield_10001")
    @Expose
    private Object customfield10001;
    @SerializedName("customfield_10002")
    @Expose
    private Object customfield10002;
    @SerializedName("customfield_10003")
    @Expose
    private Object customfield10003;
    @SerializedName("customfield_10004")
    @Expose
    private Object customfield10004;
    @SerializedName("environment")
    @Expose
    private Object environment;
    @SerializedName("duedate")
    @Expose
    private Object duedate;
    @SerializedName("progress")
    @Expose
    private Progress progress;
    @SerializedName("votes")
    @Expose
    private Votes votes;
    @SerializedName("comment")
    @Expose
    private Comment comment;
    @SerializedName("worklog")
    @Expose
    private Worklog worklog;

    public String getStatuscategorychangedate() {
        return statuscategorychangedate;
    }

    public void setStatuscategorychangedate(String statuscategorychangedate) {
        this.statuscategorychangedate = statuscategorychangedate;
    }

    public Issuetype getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(Issuetype issuetype) {
        this.issuetype = issuetype;
    }

    public Object getTimespent() {
        return timespent;
    }

    public void setTimespent(Object timespent) {
        this.timespent = timespent;
    }

    public Object getCustomfield10030() {
        return customfield10030;
    }

    public void setCustomfield10030(Object customfield10030) {
        this.customfield10030 = customfield10030;
    }

    public Object getCustomfield10031() {
        return customfield10031;
    }

    public void setCustomfield10031(Object customfield10031) {
        this.customfield10031 = customfield10031;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Object> getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(List<Object> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public Object getAggregatetimespent() {
        return aggregatetimespent;
    }

    public void setAggregatetimespent(Object aggregatetimespent) {
        this.aggregatetimespent = aggregatetimespent;
    }

    public Object getResolution() {
        return resolution;
    }

    public void setResolution(Object resolution) {
        this.resolution = resolution;
    }

    public Object getCustomfield10027() {
        return customfield10027;
    }

    public void setCustomfield10027(Object customfield10027) {
        this.customfield10027 = customfield10027;
    }

    public Object getCustomfield10028() {
        return customfield10028;
    }

    public void setCustomfield10028(Object customfield10028) {
        this.customfield10028 = customfield10028;
    }

    public Object getCustomfield10029() {
        return customfield10029;
    }

    public void setCustomfield10029(Object customfield10029) {
        this.customfield10029 = customfield10029;
    }

    public Object getResolutiondate() {
        return resolutiondate;
    }

    public void setResolutiondate(Object resolutiondate) {
        this.resolutiondate = resolutiondate;
    }

    public Integer getWorkratio() {
        return workratio;
    }

    public void setWorkratio(Integer workratio) {
        this.workratio = workratio;
    }

    public String getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(String lastViewed) {
        this.lastViewed = lastViewed;
    }

    public Watches getWatches() {
        return watches;
    }

    public void setWatches(Watches watches) {
        this.watches = watches;
    }

    public Issuerestriction getIssuerestriction() {
        return issuerestriction;
    }

    public void setIssuerestriction(Issuerestriction issuerestriction) {
        this.issuerestriction = issuerestriction;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Customfield10020> getCustomfield10020() {
        return customfield10020;
    }

    public void setCustomfield10020(List<Customfield10020> customfield10020) {
        this.customfield10020 = customfield10020;
    }

    public Object getCustomfield10021() {
        return customfield10021;
    }

    public void setCustomfield10021(Object customfield10021) {
        this.customfield10021 = customfield10021;
    }

    public Object getCustomfield10022() {
        return customfield10022;
    }

    public void setCustomfield10022(Object customfield10022) {
        this.customfield10022 = customfield10022;
    }

    public Object getCustomfield10023() {
        return customfield10023;
    }

    public void setCustomfield10023(Object customfield10023) {
        this.customfield10023 = customfield10023;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Object getCustomfield10024() {
        return customfield10024;
    }

    public void setCustomfield10024(Object customfield10024) {
        this.customfield10024 = customfield10024;
    }

    public Object getCustomfield10025() {
        return customfield10025;
    }

    public void setCustomfield10025(Object customfield10025) {
        this.customfield10025 = customfield10025;
    }

    public Object getCustomfield10026() {
        return customfield10026;
    }

    public void setCustomfield10026(Object customfield10026) {
        this.customfield10026 = customfield10026;
    }

    public List<Object> getLabels() {
        return labels;
    }

    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    public Object getCustomfield10016() {
        return customfield10016;
    }

    public void setCustomfield10016(Object customfield10016) {
        this.customfield10016 = customfield10016;
    }

    public Object getCustomfield10017() {
        return customfield10017;
    }

    public void setCustomfield10017(Object customfield10017) {
        this.customfield10017 = customfield10017;
    }

    public Customfield10018 getCustomfield10018() {
        return customfield10018;
    }

    public void setCustomfield10018(Customfield10018 customfield10018) {
        this.customfield10018 = customfield10018;
    }

    public String getCustomfield10019() {
        return customfield10019;
    }

    public void setCustomfield10019(String customfield10019) {
        this.customfield10019 = customfield10019;
    }

    public Object getAggregatetimeoriginalestimate() {
        return aggregatetimeoriginalestimate;
    }

    public void setAggregatetimeoriginalestimate(Object aggregatetimeoriginalestimate) {
        this.aggregatetimeoriginalestimate = aggregatetimeoriginalestimate;
    }

    public Object getTimeestimate() {
        return timeestimate;
    }

    public void setTimeestimate(Object timeestimate) {
        this.timeestimate = timeestimate;
    }

    public List<Object> getVersions() {
        return versions;
    }

    public void setVersions(List<Object> versions) {
        this.versions = versions;
    }

    public List<Object> getIssuelinks() {
        return issuelinks;
    }

    public void setIssuelinks(List<Object> issuelinks) {
        this.issuelinks = issuelinks;
    }

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Object> getComponents() {
        return components;
    }

    public void setComponents(List<Object> components) {
        this.components = components;
    }

    public Object getTimeoriginalestimate() {
        return timeoriginalestimate;
    }

    public void setTimeoriginalestimate(Object timeoriginalestimate) {
        this.timeoriginalestimate = timeoriginalestimate;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Object getCustomfield10010() {
        return customfield10010;
    }

    public void setCustomfield10010(Object customfield10010) {
        this.customfield10010 = customfield10010;
    }

    public Object getCustomfield10014() {
        return customfield10014;
    }

    public void setCustomfield10014(Object customfield10014) {
        this.customfield10014 = customfield10014;
    }

    public Timetracking getTimetracking() {
        return timetracking;
    }

    public void setTimetracking(Timetracking timetracking) {
        this.timetracking = timetracking;
    }

    public Object getCustomfield10015() {
        return customfield10015;
    }

    public void setCustomfield10015(Object customfield10015) {
        this.customfield10015 = customfield10015;
    }

    public Object getCustomfield10005() {
        return customfield10005;
    }

    public void setCustomfield10005(Object customfield10005) {
        this.customfield10005 = customfield10005;
    }

    public Object getCustomfield10006() {
        return customfield10006;
    }

    public void setCustomfield10006(Object customfield10006) {
        this.customfield10006 = customfield10006;
    }

    public Object getSecurity() {
        return security;
    }

    public void setSecurity(Object security) {
        this.security = security;
    }

    public Object getCustomfield10007() {
        return customfield10007;
    }

    public void setCustomfield10007(Object customfield10007) {
        this.customfield10007 = customfield10007;
    }

    public Object getCustomfield10008() {
        return customfield10008;
    }

    public void setCustomfield10008(Object customfield10008) {
        this.customfield10008 = customfield10008;
    }

    public Object getCustomfield10009() {
        return customfield10009;
    }

    public void setCustomfield10009(Object customfield10009) {
        this.customfield10009 = customfield10009;
    }

    public Object getAggregatetimeestimate() {
        return aggregatetimeestimate;
    }

    public void setAggregatetimeestimate(Object aggregatetimeestimate) {
        this.aggregatetimeestimate = aggregatetimeestimate;
    }

    public List<Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Object> attachment) {
        this.attachment = attachment;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public List<Object> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Object> subtasks) {
        this.subtasks = subtasks;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public Aggregateprogress getAggregateprogress() {
        return aggregateprogress;
    }

    public void setAggregateprogress(Aggregateprogress aggregateprogress) {
        this.aggregateprogress = aggregateprogress;
    }

    public Object getCustomfield10001() {
        return customfield10001;
    }

    public void setCustomfield10001(Object customfield10001) {
        this.customfield10001 = customfield10001;
    }

    public Object getCustomfield10002() {
        return customfield10002;
    }

    public void setCustomfield10002(Object customfield10002) {
        this.customfield10002 = customfield10002;
    }

    public Object getCustomfield10003() {
        return customfield10003;
    }

    public void setCustomfield10003(Object customfield10003) {
        this.customfield10003 = customfield10003;
    }

    public Object getCustomfield10004() {
        return customfield10004;
    }

    public void setCustomfield10004(Object customfield10004) {
        this.customfield10004 = customfield10004;
    }

    public Object getEnvironment() {
        return environment;
    }

    public void setEnvironment(Object environment) {
        this.environment = environment;
    }

    public Object getDuedate() {
        return duedate;
    }

    public void setDuedate(Object duedate) {
        this.duedate = duedate;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Votes getVotes() {
        return votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Worklog getWorklog() {
        return worklog;
    }

    public void setWorklog(Worklog worklog) {
        this.worklog = worklog;
    }

}
