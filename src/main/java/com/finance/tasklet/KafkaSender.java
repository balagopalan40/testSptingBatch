package com.finance.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	String kafkaTopic = "quickstart-events";
	
	public void send(String message) {
	    
	    kafkaTemplate.send(kafkaTopic, message);
	}

}
