package com.finance.jobs;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.finance.tasklet.FileDeletingTasklet;

@Configuration
@EnableBatchProcessing
public class ProcessPeopleSoftAPI {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private SimpleJobLauncher jobLauncher1;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Scheduled(cron = "*/5 * * * * *")
	public void perform() throws Exception {

		System.out.println("Job Started at :" + new Date());

		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher1.run(readFiles(), param);

		System.out.println("Job finished with status :" + execution.getStatus());
	}
	
	@Bean
	public Job readFiles() {
		return jobBuilderFactory.get("processAPI").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(fileDeletingTasklet()).build();
	}
	
	@Bean
	public FileDeletingTasklet fileDeletingTasklet() {
		FileDeletingTasklet tasklet = new FileDeletingTasklet();
		
		return tasklet;
	}

	@Bean
	public SimpleJobLauncher jobLauncher1(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
}
