package io.spring.batch.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobLoggerListener implements JobExecutionListener {

    private static String START_NESSAGE = "%s is beginning execution";
    private static String END_NESSAGE =
            "%s has completed with the status %s";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(String.format(START_NESSAGE,
                    jobExecution.getJobInstance().getJobName()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(String.format(END_NESSAGE,
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getStatus()));
    }
}
