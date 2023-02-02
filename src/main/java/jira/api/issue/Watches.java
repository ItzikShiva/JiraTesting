
package jira.api.issue;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Watches {

    @SerializedName("self")
    @Expose
    private String self;
    @SerializedName("watchCount")
    @Expose
    private Integer watchCount;
    @SerializedName("isWatching")
    @Expose
    private Boolean isWatching;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

    public Boolean getIsWatching() {
        return isWatching;
    }

    public void setIsWatching(Boolean isWatching) {
        this.isWatching = isWatching;
    }

}
