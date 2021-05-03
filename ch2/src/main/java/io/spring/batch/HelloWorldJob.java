package io.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicJob")
                .start(step1())
                .build();
    }

    /*@Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("Hello, World");
                    return RepeatStatus.FINISHED;
                }).build();
    }*/

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(helloWorldTasklet())
                .build();
    }

    /*@Bean
    public Tasklet helloWorldTasklet() {

        return (stepContribution, chunkContext) -> {
                            String name = (String) chunkContext.getStepContext()
                                    .getJobParameters()
                                    .get("name");

                            System.out.println(String.format("Hello, %s!", name));
                            return RepeatStatus.FINISHED;
        };
    }*/

    @Bean
    public Tasklet helloWorldTasklet(@Value("#{jobParameters['name']}") String name) {

        return (stepContribution, chunkContext) -> {
            System.out.println(String.format("Hello, %s!", name));
            return RepeatStatus.FINISHED;
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldJob.class, args);
    }

}
