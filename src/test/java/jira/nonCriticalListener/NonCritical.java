package jira.nonCriticalListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface NonCritical {
    String bugKey();
}