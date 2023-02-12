package jira.api.issue.baseissuerequest;

public class Label {
    public String add;
    public String remove;

    public Label(String add) {
        this.add = add;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }
}


