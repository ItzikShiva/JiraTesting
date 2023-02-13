
package jira.api.issue.issuerequest;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Update {

    public List<Label> labels;

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
