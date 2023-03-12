package jira.api.issue;

import jira.nonCriticalListener.NonCritical;
import jira.nonCriticalListener.NonCriticalListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static jira.api.issue.IssueConstants.CLOSED_BUG_KEY;
import static jira.api.issue.IssueConstants.OPEN_BUG_KEY;

@Listeners(NonCriticalListener.class)
public class GetIssueNonCriticalTests extends BaseIssueTests {

    @BeforeClass
    public static void login() {
        apiService.login();
    }

    @NonCritical(bugKey = CLOSED_BUG_KEY)
    @Test
    public static void failedTestAndClosedBug() {
        Assert.assertEquals(0, 1);
    }

    @NonCritical(bugKey = OPEN_BUG_KEY)
    @Test
    public static void failedTestAndOpenBug() {
        Assert.assertEquals(0, 1);
    }

    @NonCritical(bugKey = CLOSED_BUG_KEY)
    @Test
    public static void PassedTestAndClosedBug() {
        Assert.assertEquals(0, 0);
    }

    @NonCritical(bugKey = OPEN_BUG_KEY)
    @Test
    public static void PassedTestAndOpenBug() {
        Assert.assertEquals(0, 0);
    }

}
