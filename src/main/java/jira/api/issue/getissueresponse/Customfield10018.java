
package jira.api.issue.getissueresponse;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Customfield10018 {

    @SerializedName("hasEpicLinkFieldDependency")
    @Expose
    private Boolean hasEpicLinkFieldDependency;
    @SerializedName("showField")
    @Expose
    private Boolean showField;
    @SerializedName("nonEditableReason")
    @Expose
    private NonEditableReason nonEditableReason;

    public Boolean getHasEpicLinkFieldDependency() {
        return hasEpicLinkFieldDependency;
    }

    public void setHasEpicLinkFieldDependency(Boolean hasEpicLinkFieldDependency) {
        this.hasEpicLinkFieldDependency = hasEpicLinkFieldDependency;
    }

    public Boolean getShowField() {
        return showField;
    }

    public void setShowField(Boolean showField) {
        this.showField = showField;
    }

    public NonEditableReason getNonEditableReason() {
        return nonEditableReason;
    }

    public void setNonEditableReason(NonEditableReason nonEditableReason) {
        this.nonEditableReason = nonEditableReason;
    }

}
