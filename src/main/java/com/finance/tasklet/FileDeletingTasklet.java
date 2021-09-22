package com.finance.tasklet;

import java.io.File;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import ch.qos.logback.core.net.SyslogOutputStream;

public class FileDeletingTasklet implements Tasklet, InitializingBean {

	private Resource directory;
	
	@Autowired
	PeopleSoftApi api;
	
	@Autowired
	KafkaSender kafkaSender;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Inside the tasklet");
		System.out.println(api.createPost().getTitle());
		kafkaSender.send("Message inserted");
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//Assert.notNull(directory, "directory must be set");
	}

	public Resource getDirectory() {
		return directory;
	}

	public void setDirectory(Resource directory) {
		this.directory = directory;
	}
}
