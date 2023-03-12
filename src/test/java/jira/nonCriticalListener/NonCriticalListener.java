package jira.nonCriticalListener;

import jira.api.issue.BaseIssueTests;
import jira.api.issue.getissueresponse.GetIssueResponse;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static jira.api.APIUtils.responseToObject;

public class NonCriticalListener extends BaseIssueTests implements IInvokedMethodListener {
    private static final Logger logger = LogManager.getLogger(NonCriticalListener.class);

    /**
     * isBugOpen(String issueKey)
     * If the response code isn't 200, the method returns false - bug is NOT open.
     * Then it checks if the issue has a resolution or not. If the issue has a resolution, it means that it is DONE, so the method returns false.
     * If the issue does not have a resolution, it means that it is OPEN, so the method returns true.
     */
    private static boolean isBugOpen(String issueKey) {
        Response response = issueService.getIssue(issueKey);

        if (response.code() != 200) {
            return false;
        }

        GetIssueResponse getIssueResponse = responseToObject(response, GetIssueResponse.class);

        return getIssueResponse.getFields().getResolution() == null;
    }


    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(NonCritical.class)) {

            NonCritical annotation = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(NonCritical.class);
            String bugKey = annotation.bugKey();

            if (testResult.getStatus() == ITestResult.SUCCESS) {
                caseTestSuccess(testResult, bugKey);
            } else {
                caseTestFailed(testResult, bugKey);
            }
        }
    }

    private void caseTestSuccess(ITestResult testResult, String bugKey) {
        if (isBugOpen(bugKey)) {
            logger.error("test originally was passed. and BUG with: " + bugKey + " is open. please close that bug!");
            testResult.setStatus(ITestResult.FAILURE);
        } else {
            logger.warn("remove unneeded annotation from this test");
        }
    }

    private void caseTestFailed(ITestResult testResult, String bugKey) {
        if (isBugOpen(bugKey)) {
            logger.error("test originally was failed. BUG with: " + bugKey + " already open. test was skipped");
            testResult.setStatus(ITestResult.SKIP);
        } else {
            logger.error("investigation for the test and bug: " + bugKey + "required!");
        }
    }
}