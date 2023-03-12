package jira.nonCritical;

import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public interface IInvokedMethodListenerWithLogger extends IInvokedMethodListener {
    void afterInvocation(IInvokedMethod method, ITestResult testResult, Logger logger);
}